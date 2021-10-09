package vn.com.viettel.vds.model.mapper;

public interface Mapper <E,U>{

    E mapToDTO(U u);
    U mapToEntity(E e);
}
