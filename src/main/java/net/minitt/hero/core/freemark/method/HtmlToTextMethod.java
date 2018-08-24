package net.minitt.hero.core.freemark.method;

import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import net.minitt.hero.core.utils.HeroUtils;

public class HtmlToTextMethod implements TemplateMethodModelEx {

	@Override
	public Object exec(List args) throws TemplateModelException {
		String txt = "";
		int len = -1;
		if(args.size() == 2) {
			TemplateScalarModel tsm = (TemplateScalarModel) args.get(0);
			txt = tsm.getAsString();
			TemplateNumberModel tsm2 = (TemplateNumberModel) args.get(1);
			len = tsm2.getAsNumber().intValue();
		}else if(args.size() == 1) {
			TemplateScalarModel tsm = (TemplateScalarModel) args.get(0);
			txt = tsm.getAsString();
		}else {
			throw new TemplateModelException(
                    "Error: Expecting 1 or 2 string arguments here");
		}
		txt = HeroUtils.htmlToText(txt);
		if(len!=-1) {
			if(len<txt.length()) {
				txt = txt.substring(0, len-1);
			}
		}
		return txt;
	}

}
