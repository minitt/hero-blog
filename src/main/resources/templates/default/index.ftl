<#include "common/header.ftl">
<div class="main-content index-page clearfix">
	<div class="note">
		<ul class="note-list">
		<#if aPage??>
		<#list aPage.content as a>
			<li class="have-img">
			  <div class="content">
			    <a class="title" target="_blank" href="/a/${a.id!}">${a.title}</a>
			    <p class="abstract">
			      ${htmlToText(mdToHtml(a.content),75)}...
			    </p>
			  </div>
			</li>
		</#list>
		</#if>
		</ul>
	</div>
</div>
<#include "common/footer.ftl">