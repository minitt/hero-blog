package net.minitt.hero.core.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.util.StringUtils;

import com.vdurmont.emoji.EmojiParser;

public class HeroUtils {

	public static final String MP3_PREFIX = "[mp3:";
	public static final String MUSIC_IFRAME = "<iframe frameborder=\"no\" border=\"0\" marginwidth=\"0\" marginheight=\"0\" width=350 height=106 src=\"//music.163.com/outchain/player?type=2&id=$1&auto=0&height=88\"></iframe>";
	public static final String MUSIC_REG_PATTERN = "\\[mp3:(\\d+)\\]";
	public static final String GIST_PREFIX_URL = "https://gist.github.com/";
	public static final String GIST_REG_PATTERN = "&lt;script src=\"https://gist.github.com/(\\w+)/(\\w+)\\.js\">&lt;/script>";
	public static final String GIST_REPLATE_PATTERN = "<script src=\"https://gist.github.com/$1/$2\\.js\"></script>";

	/**
	 * markdown转换为html
	 *
	 * @param markdown
	 * @return
	 */
	public static String mdToHtml(String markdown) {
		if (StringUtils.isEmpty(markdown)) {
			return "";
		}

		List<Extension> extensions = Arrays.asList(TablesExtension.create());
		Parser parser = Parser.builder().extensions(extensions).build();
		Node document = parser.parse(markdown);
		HtmlRenderer renderer = HtmlRenderer.builder().attributeProviderFactory(context -> new LinkAttributeProvider())
				.extensions(extensions).build();

		String content = renderer.render(document);
		content = EmojiParser.parseToUnicode(content);

		// 支持网易云音乐输出
		if (content.contains(MP3_PREFIX)) {
			content = content.replaceAll(MUSIC_REG_PATTERN, MUSIC_IFRAME);
		}
		// 支持gist代码输出
		if (content.contains(GIST_PREFIX_URL)) {
			content = content.replaceAll(GIST_REG_PATTERN, GIST_REPLATE_PATTERN);
		}
		return content;
	}

	static class LinkAttributeProvider implements AttributeProvider {

		@Override
		public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
			if (node instanceof Link) {
				attributes.put("target", "_blank");
			}
		}
	}

	/**
	 * 提取html中的文字
	 *
	 * @param html
	 * @return
	 */
	public static String htmlToText(String html) {
		if (!StringUtils.isEmpty(html)) {
			return html.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
		}
		return "";
	}

	/**
	 * 替换HTML脚本
	 *
	 * @param value
	 * @return
	 */
	public static String cleanXSS(String value) {
		// You'll need to remove the spaces from the html entities below
		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		value = value.replaceAll("'", "&#39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");
		return value;
	}
}
