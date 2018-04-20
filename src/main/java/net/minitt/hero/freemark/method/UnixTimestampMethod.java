package net.minitt.hero.freemark.method;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

public class UnixTimestampMethod implements TemplateMethodModelEx{

	@Override
	public Object exec(List args) throws TemplateModelException {
		long timestamp = 0;
		String formatter = "yyyy-MM-dd HH:mm:ss";
		if(args.size() == 2) {
			TemplateNumberModel tsm = (TemplateNumberModel) args.get(0);
			timestamp = tsm.getAsNumber().longValue();
			TemplateScalarModel tsm2 = (TemplateScalarModel) args.get(1);
			formatter = tsm2.getAsString();
		}else if(args.size() == 1) {
			TemplateNumberModel tsm = (TemplateNumberModel) args.get(0);
			timestamp = tsm.getAsNumber().longValue();
		}else {
			throw new TemplateModelException(
                    "Error: Expecting 1 or 2 string arguments here");
		}
		return new SimpleDateFormat(formatter).format(new Date(timestamp));
	}

}
