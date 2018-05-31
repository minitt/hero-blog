<#include "common/macro-head.ftl">
<!DOCTYPE html>
<html>
    <head>
	    <@head title="HEIRUO">
	    </@head>
    </head>
    <body>
    	<#include "common/header.ftl">
    	<main>
			<#if aPage??>
			<#list aPage.content as a>
			<article class="article-item">
				<h1><a href="/a/${a.id!}">${a.title}</a></h1>
				<div class="tips">
					<#if a.tagSet??>
						<#list a.tagSet as tag>
		                    <a class="tag" href="">${tag.name}</a>
		                </#list>
					</#if>
                    <span class="article__responsive-meta">
                        <time class="ft-nowrap"><i class="icon iconfont icon-time"></i>${unixTimestamp(a.createdTime)}</time>
                        •
                        <span class="ft-nowrap">${a.hits!0} 浏览</span>
                        •
                        <a href="">${a.commentsNum!0} 评论</a>
                    </span>
                </div>
				<p>${htmlToText(mdToHtml(a.content),75)}......</p>
			</article>
			</#list>
			</#if>
			<#include "common/footer.ftl">
		</main>
    </body>
</html>