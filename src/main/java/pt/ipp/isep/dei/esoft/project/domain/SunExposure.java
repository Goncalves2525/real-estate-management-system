package pt.ipp.isep.dei.esoft.project.domain;

public enum SunExposure {
         NORTH,
         SOUTH,
         WEST,
         EAST;

        @Override
        public String toString() {
            switch(this) {
                case NORTH:
                    return "North";
                case SOUTH:
                    return "South";
                case WEST:
                    return "West";
                case EAST:
                    return "East";
                default:
                    return "";
            }
        }
}
