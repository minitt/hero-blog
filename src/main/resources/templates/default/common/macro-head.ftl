<#macro head title>
<meta charset="utf-8" />
<title>${title}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="author" content="minitt" />
<meta name="copyright" content="HEIRUO" />
<meta http-equiv="Window-target" content="_top" />
<link href="${ctx.contextPath}/res/default/css/style.css" rel="stylesheet">
<link href="${ctx.contextPath}/res/default/css/md.css" rel="stylesheet">
<link href="${ctx.contextPath}/res/default/css/xcode.min.css" rel="stylesheet">
<link href="${ctx.contextPath}/res/default/font/iconfont.css" rel="stylesheet">

<script src="${ctx.contextPath}/res/default/js/jquery.min.js"></script>
<script src="${ctx.contextPath}/res/default/js/instantclick.min.js"></script>
<script src="${ctx.contextPath}/res/default/js/hero.js"></script>
<#nested>
</#macro>