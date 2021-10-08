package vn.com.viettel.vds.model.converter;

public interface Converter <E,U>{

    E convert(U u);
}
