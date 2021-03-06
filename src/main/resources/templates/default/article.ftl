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
			<article class="main-content page-page">
			    <div class="post-header">
			        <h1 class="post-title" itemprop="name headline">${article.title}</h1>
			        <div class="post-data">
			            <time datetime="${unixTimestamp(article.createdTime,'yyyy-MM-dd')}" itemprop="datePublished">发布于 ${unixTimestamp(article.createdTime,'yyyy-MM-dd')}</time>
			        </div>
			    </div>
			    <div id="post-content" class="markdown-body">${(mdToHtml(article.content))!}</div>
			</article>
			<div id="directory-content" class="directory-content">
			    <div id="directory"></div>
			</div>
			<script>
			    $('#directory').html('');
			    var postDirectoryBuild = function() {
			        var postChildren = function children(childNodes, reg) {
			                var result = [],
			                    isReg = typeof reg === 'object',
			                    isStr = typeof reg === 'string',
			                    node, i, len;
			                for (i = 0, len = childNodes.length; i < len; i++) {
			                    node = childNodes[i];
			                    if ((node.nodeType === 1 || node.nodeType === 9) &&
			                        (!reg ||
			                        isReg && reg.test(node.tagName.toLowerCase()) ||
			                        isStr && node.tagName.toLowerCase() === reg)) {
			                        result.push(node);
			                    }
			                }
			                return result;
			            },
			            createPostDirectory = function(article, directory, isDirNum) {
			                var contentArr = [],
			                    titleId = [],
			                    levelArr, root, level,
			                    currentList, list, li, link, i, len;
			                levelArr = (function(article, contentArr, titleId) {
			                    var titleElem = postChildren(article.childNodes, /^h\d$/),
			                        levelArr = [],
			                        lastNum = 1,
			                        lastRevNum = 1,
			                        count = 0,
			                        guid = 1,
			                        id = 'directory' + (Math.random() + '').replace(/\D/, ''),
			                        lastRevNum, num, elem;
			                    while (titleElem.length) {
			                        elem = titleElem.shift();
			                        contentArr.push(elem.innerHTML);
			                        num = +elem.tagName.match(/\d/)[0];
			                        if (num > lastNum) {
			                            levelArr.push(1);
			                            lastRevNum += 1;
			                        } else if (num === lastRevNum ||
			                            num > lastRevNum && num <= lastNum) {
			                            levelArr.push(0);
			                            lastRevNum = lastRevNum;
			                        } else if (num < lastRevNum) {
			                            levelArr.push(num - lastRevNum);
			                            lastRevNum = num;
			                        }
			                        count += levelArr[levelArr.length - 1];
			                        lastNum = num;
			                        elem.id = elem.id || (id + guid++);
			                        titleId.push(elem.id);
			                    }
			                    if (count !== 0 && levelArr[0] === 1) levelArr[0] = 0;
			
			                    return levelArr;
			                })(article, contentArr, titleId);
			                currentList = root = document.createElement('ul');
			                dirNum = [0];
			                for (i = 0, len = levelArr.length; i < len; i++) {
			                    level = levelArr[i];
			                    if (level === 1) {
			                        list = document.createElement('ul');
			                        if (!currentList.lastElementChild) {
			                            currentList.appendChild(document.createElement('li'));
			                        }
			                        currentList.lastElementChild.appendChild(list);
			                        currentList = list;
			                        dirNum.push(0);
			                    } else if (level < 0) {
			                        level *= 2;
			                        while (level++) {
			                            if (level % 2) dirNum.pop();
			                            currentList = currentList.parentNode;
			                        }
			                    }
			                    dirNum[dirNum.length - 1]++;
			                    li = document.createElement('li');
			                    link = document.createElement('a');
			                    link.href = '#' + titleId[i];
			                    link.innerHTML = !isDirNum ? contentArr[i] :
			                        dirNum.join('.') + ' ' + contentArr[i] ;
			                    li.appendChild(link);
			                    currentList.appendChild(li);
			                }
			                directory.appendChild(root);
			            };
			        createPostDirectory(document.getElementById('post-content'),document.getElementById('directory'), true);
			    };
			    postDirectoryBuild();
			</script>
			<#include "common/footer.ftl">
		</main>
		<script src="${ctx.contextPath}/res/default/js/highlight.min.js"></script>
		<script>hljs.initHighlightingOnLoad();</script>
    </body>
</html>
