package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * Sun Exposure, North, South, West, East.
 */

public enum SunExposure implements Serializable {
         NORTH,
         SOUTH,
         WEST,
         EAST,
        N,
        S,
        W,
        E,
         NA;

         /**
          * Returns a string representation of the object.
          *
          * @return string representation of the object.
          */
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
