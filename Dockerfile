# Multi-stage build for Real Estate USA System with VNC support

# Stage 1: Build stage
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application (Linux profile will be auto-activated)
RUN mvn clean package -DskipTests

# Stage 2: Runtime stage with VNC
FROM eclipse-temurin:17-jre

# Install system dependencies
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    # JavaFX dependencies
    libgtk-3-0 \
    libgl1 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    libxxf86vm1 \
    # VNC and X11 virtual display
    xvfb \
    x11vnc \
    # Window manager for better UX
    fluxbox \
    # Utilities
    wget \
    maven \
    supervisor \
    && rm -rf /var/lib/apt/lists/*

# Install noVNC
RUN mkdir -p /opt/noVNC/utils/websockify && \
    wget -qO- https://github.com/novnc/noVNC/archive/refs/tags/v1.4.0.tar.gz | tar xz --strip 1 -C /opt/noVNC && \
    wget -qO- https://github.com/novnc/websockify/archive/refs/tags/v0.11.0.tar.gz | tar xz --strip 1 -C /opt/noVNC/utils/websockify && \
    ln -s /opt/noVNC/vnc.html /opt/noVNC/index.html

# Set working directory
WORKDIR /app

# Copy Maven build from builder stage
COPY --from=builder /app/target ./target
COPY --from=builder /app/pom.xml ./pom.xml
COPY --from=builder /root/.m2 /root/.m2

# Create startup script
RUN echo '#!/bin/bash\n\
# Start Xvfb\n\
Xvfb :99 -screen 0 1280x720x24 &\n\
export DISPLAY=:99\n\
\n\
# Wait for X server\n\
sleep 2\n\
\n\
# Start window manager\n\
fluxbox &\n\
\n\
# Start x11vnc\n\
x11vnc -display :99 -forever -shared -rfbport 5900 &\n\
\n\
# Wait for VNC server\n\
sleep 2\n\
\n\
# Start noVNC\n\
/opt/noVNC/utils/novnc_proxy --vnc localhost:5900 --listen 6080 &\n\
\n\
# Wait a bit\n\
sleep 2\n\
\n\
# Start the application\n\
cd /app\n\
mvn javafx:run\n\
' > /start.sh && chmod +x /start.sh

# Environment variables
ENV DISPLAY=:99
ENV RESOLUTION=1280x720

# Expose VNC and noVNC ports
EXPOSE 5900 6080

# Volume for data persistence
VOLUME ["/app/data"]

# Start everything
CMD ["/start.sh"]
