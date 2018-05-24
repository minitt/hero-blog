<footer id="footer" class="footer bg-white">
    <div class="footer-social">
        <div class="footer-container clearfix">
            <div class="social-list">
			    <a class="social weibo" target="blank" href="http://weibo.com/">微博</a>|<a class="social github" target="blank" href="https://github.com/">Github</a>
			</div>
	        <div class="ipc">©2018 <a href="http://www.miitbeian.gov.cn/">滇ICP备18001446号</a></div>
        </div>
    </div>
</footer>
<script type="text/javascript">
    var header = new Headroom(document.getElementById("header"), {
        tolerance: 10,
        offset : 80,
        classes: {
            initial: "animated",
            pinned: "slideDown",
            unpinned: "slideUp"
        }
    });
    header.init();
    $('#search-inp').keypress(function (e) {
        var key = e.which; //e.which是按键的值
        if (key == 13) {
            var q = $(this).val();
            if (q && q != '') {
                window.location.href = '/search/' + q;
            }
        }
    });
</script>
<script data-no-instant>
    InstantClick.on('change', function (isInitialLoad) {
        var blocks = document.querySelectorAll('pre code');
        for (var i = 0; i < blocks.length; i++) {
            hljs.highlightBlock(blocks[i]);
        }
        if (isInitialLoad === false) {
            if (typeof ga !== 'undefined') ga('send', 'pageview', location.pathname + location.search);
        }
    });
    InstantClick.init('mousedown');
</script>