package com.ambovombe.configuration.mapping;

import lombok.Getter;
import lombok.Setter;

public class Constraint {
    @Getter @Setter
    String primaryKey;
    @Getter @Setter
    String[] foreignKey;
}
