<#include "common/macro-head.ftl">
<!DOCTYPE html>
<html>
    <head>
	    <@head title="HEIRUO" />
	    <script src="${ctx.contextPath}/res/default/js/jquery.keyword.highlight.js"></script>
	    <style>
	    	.highlight{
	    		background-color: #FFFF88;
	    	}
	    </style>
	    <script>
	    	$(function($){
	    		$('.article-item').highlight('${keyword!}');
	    	});
	    </script>
    </head>
    <body>
    	<#include "common/header.ftl">
    	<main>
			<#if sPage??>
			<#list sPage.content as a>
			<article class="article-item">
				<h1><a href="/a/${a.id!}">${a.title}</a></h1>
				<div class="tips">
                    <span class="article__responsive-meta">
                        <time class="ft-nowrap"><i class="icon iconfont icon-time"></i>${unixTimestamp(a.createdTime)}</time>
                    </span>
                </div>
				<p>${a.content?substring(0,75)}......</p>
			</article>
			</#list>
			</#if>
			<#include "common/footer.ftl">
		</main>
    </body>
</html>