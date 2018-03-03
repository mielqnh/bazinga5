package be.mielnoelanders.bazinga.mappers;

import be.mielnoelanders.bazinga.domain.AccessoryType;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AccessoryTypeEnumMapperTest {

    @Before
    public void init(){}

    @Test(expected =RuntimeException.class)
    public void whenAccessoryTypeDoesNotExist(){
        AccessoryTypeEnumMapper.mapToAccessoryType(100);
    }

    @Test
    public void whenAccessoryTypeDoesExist(){
        AccessoryType result = AccessoryTypeEnumMapper.mapToAccessoryType(1);
        assertThat(result).isEqualTo(AccessoryType.DICE);
    }

}
