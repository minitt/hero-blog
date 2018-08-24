<header>
    <div class="wrapper">
        <a href="${ctx.contextPath}/" class="logo">
            <img src="${ctx.contextPath}/res/default/img/logo.png" style="background-image: none; background-color: transparent;">
        </a>
        <a href="">
        	<i class="icon iconfont icon-category"></i>
			 分类
        </a>
        <a href="">
        	<i class="icon iconfont icon-tag"></i>
           	 标签
        </a>
        <a href="">
        	<i class="icon iconfont icon-save"></i>
			存档
        </a>
        <form action="${ctx.contextPath}/search" class="search-form" id="search-form">
            <input type="text" placeholder="搜索..." value="${keyword!}" maxlength="30" name="keyword" />
            <a href="javascript:void(0);" onclick="$('#search-form').submit();"><i class="icon iconfont icon-search"></i></a>
        </form>
    </div>
</header>