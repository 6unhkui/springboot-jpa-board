package springjpa.board.domain.user.converter;

import springjpa.board.domain.user.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

//@Convert
//public class RoleConverter implements AttributeConverter<Role, Integer> {
//    @Override
//    public Integer convertToDatabaseColumn(Role attribute) {
//        if(attribute == null) return null;
//        return attribute.getValue();
//    }
//
//    @Override
//    public Role convertToEntityAttribute(Integer dbData) {
//        if(dbData == null) return null;
//        return Role.findByValue(dbData);
//    }
//}
