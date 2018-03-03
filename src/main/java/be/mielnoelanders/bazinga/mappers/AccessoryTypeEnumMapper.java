package be.mielnoelanders.bazinga.mappers;

import be.mielnoelanders.bazinga.domain.AccessoryType;

public class AccessoryTypeEnumMapper {

    public static AccessoryType mapToAccessoryType(int type) {

        for (AccessoryType accessoryType : AccessoryType.values()) {
            if (accessoryType.ordinal() == type) {
                return accessoryType;
            }
        }
        throw new RuntimeException("AccessoryType not found!");
    }
}
