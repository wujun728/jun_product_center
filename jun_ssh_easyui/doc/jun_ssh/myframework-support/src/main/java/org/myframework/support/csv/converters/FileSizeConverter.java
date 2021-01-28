package org.myframework.support.csv.converters;

import org.apache.commons.validator.GenericValidator;
import org.myframework.commons.util.io.FileUtils;
import org.myframework.support.csv.Converter;
import org.myframework.support.csv.annotation.CsvConverter;
import org.springframework.stereotype.Component;

@Component("fileSizeConverter")
@CsvConverter
public class FileSizeConverter implements Converter {
	@Override
	public String convert(Object value) {
		if(value!=null && GenericValidator.isLong(value.toString()))
			return FileUtils.byteCountToDisplaySize(Long.valueOf(value.toString()));
		else {
			return  "";
		}
	}
}
