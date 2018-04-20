package net.minitt.hero.freemark.method;

import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import net.minitt.hero.utils.HeroUtils;

public class MdToHtmlMethod implements TemplateMethodModelEx{

	@Override
	public Object exec(List args) throws TemplateModelException {
		String txt = "";
		if(args.size() == 1) {
			TemplateScalarModel tsm = (TemplateScalarModel) args.get(0);
			txt = tsm.getAsString();
		}else {
			throw new TemplateModelException(
                    "Error: Expecting 1 string arguments here");
		}
		txt = HeroUtils.mdToHtml(txt);
		return txt;
	}

}
