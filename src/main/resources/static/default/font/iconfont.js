(function(window){var svgSprite='<svg><symbol id="icon-msg" viewBox="0 0 1024 1024"><path d="M2.281631 985.838602c41.419806-82.382291 87.400388-195.46967 85.044194-274.320155-55.800544-67.285748-87.325825-153.995184-87.325825-242.310214 0-248.998524 241.559612-431.051806 512-431.051806 268.755262 0 512 180.683806 512 431.051806 0 260.702447-287.222058 504.173359-681.305476 407.397903C271.118913 920.854369 113.29367 965.592233 2.281631 985.838602z"  ></path></symbol><symbol id="icon-category" viewBox="0 0 1024 1024"><path d="M129.253806 115.734908c-35.96205 0-65.129346 29.086455-65.129346 64.959477 0 35.904745 29.167297 64.98506 65.129346 64.98506 35.96819 0 65.135486-29.080316 65.135486-64.98506C194.389292 144.82034 165.221995 115.734908 129.253806 115.734908zM294.588365 209.637578l634.82028 0c17.188474 0 31.118741-13.873985 31.118741-30.994921s-13.930267-31.001061-31.118741-31.001061L294.588365 147.641595c-17.187451 0-31.118741 13.879102-31.118741 31.001061S277.400914 209.637578 294.588365 209.637578zM129.156592 586.847685c35.96819 0 65.135486-29.086455 65.135486-64.98813 0-35.874045-29.167297-64.962547-65.135486-64.962547-35.963073 0-65.13037 29.088502-65.13037 64.962547C64.027246 557.76123 93.193519 586.847685 129.156592 586.847685zM929.311431 488.802672 294.490128 488.802672c-17.187451 0-31.117718 13.879102-31.117718 30.997991 0 17.121959 13.930267 31.001061 31.117718 31.001061l634.821303 0c17.188474 0 31.117718-13.879102 31.117718-31.001061C960.429149 502.682797 946.499905 488.802672 929.311431 488.802672zM129.162732 782.311449c-35.96205 0-65.129346 29.083385-65.129346 64.959477 0 35.901675 29.167297 64.9912 65.129346 64.9912 35.96819 0 65.136509-29.088502 65.136509-64.9912C194.299241 811.394834 165.130921 782.311449 129.162732 782.311449zM929.311431 810.149471 294.497291 810.149471c-17.187451 0-31.117718 13.873985-31.117718 30.994921 0 17.118889 13.930267 30.994921 31.117718 30.994921l634.815163 0c17.188474 0 31.117718-13.877055 31.117718-30.994921C960.429149 824.023456 946.499905 810.149471 929.311431 810.149471z"  ></path></symbol><symbol id="icon-time" viewBox="0 0 1024 1024"><path d="M667.573 511.265H512.352V267.336c0-11.512-10.617-22.129-22.129-22.129h-44.386c-11.512 0-22.128 10.617-22.128 22.13v310.442c0 11.512 10.616 22.193 22.192 22.193h221.736c11.512 0 22.193-10.617 22.193-22.193v-44.322c-0.064-11.576-10.745-22.192-22.257-22.192z m315.751-198.648C929.729 185.92 837.696 93.887 710.999 40.292a509.986 509.986 0 0 0-397.359 0C187.007 93.887 94.271 185.6 40.548 312.617c-53.595 126.697-53.595 270.662 0 397.359 53.723 127.017 146.204 219.433 273.156 273.156 126.697 53.595 270.662 53.595 397.36 0C838.08 929.41 929.728 836.61 983.387 709.976c53.659-127.017 53.659-270.406-0.064-397.359zM840.51 701.981c-33.513 57.753-79.945 103.545-138.337 137.442-57.752 33.513-121.516 50.525-189.757 50.525-68.305 0-132.261-16.628-190.717-50.525-57.752-33.513-103.993-79.69-137.506-137.442-33.896-58.455-50.589-122.411-50.589-190.716 0-68.306 17.076-132.07 50.59-189.822 33.896-58.456 79.689-104.888 137.505-138.337 58.456-33.897 122.412-50.525 190.717-50.525 68.24 0 132.005 17.076 189.757 50.525 58.456 33.897 104.44 79.945 138.337 138.337 33.513 57.752 50.59 121.516 50.59 189.822 0 68.305-16.693 132.26-50.59 190.716z"  ></path></symbol><symbol id="icon-save" viewBox="0 0 1024 1024"><path d="M725.333333 128H213.333333a85.333333 85.333333 0 0 0-85.333333 85.333333v597.333334a85.333333 85.333333 0 0 0 85.333333 85.333333h597.333334c46.933333 0 85.333333-38.4 85.333333-85.333333V298.666667l-170.666667-170.666667z m-213.333333 682.666667c-70.826667 0-128-57.173333-128-128s57.173333-128 128-128 128 57.173333 128 128-57.173333 128-128 128z m128-426.666667H213.333333V213.333333h426.666667v170.666667z"  ></path></symbol><symbol id="icon-search" viewBox="0 0 1024 1024"><path d="M1012 947.52L717.12 652.64a390.56 390.56 0 1 0-65.76 69.92L944 1015.2z m-912-525.28a302.72 302.72 0 1 1 302.4 302.72A303.04 303.04 0 0 1 99.84 422.24z" fill="" ></path></symbol><symbol id="icon-People_Plus_Asset" viewBox="0 0 1028 1024"><path d="M700.374254 531.149535l-3.808214-7.929763c-28.441097-59.485277-95.32588-100.893586-169.104014-100.893586-33.309827 38.708815-84.744828 62.956055-141.747534 62.956055h-0.819489c-57.026809 0-108.46181-24.24724-141.747535-62.956055-73.802236 0-140.662916 41.408309-169.104013 100.893586l-3.687701 7.929763A686.659668 686.659668 0 0 0 0.000193 834.432859a705.074073 705.074073 0 0 0 26.030834 189.567141H744.771289a705.025868 705.025868 0 0 0 26.030834-189.567141 686.659668 686.659668 0 0 0-70.427869-303.283324z"  ></path><path d="M202.462238 241.026245a182.842509 182.842509 0 1 0 182.987125-182.432765h-0.289231A182.625586 182.625586 0 0 0 202.462238 241.026245zM385.32885 55.677062z"  ></path></symbol><symbol id="icon-chakan" viewBox="0 0 1434 1024"><path d="M691.96660156 62C241.96660156 62 62 512 62 512s179.96660156 450 629.96660156 450c450 0 629.96748047-450 629.96748047-450S1141.80224609 62 691.96660156 62z m0 719.86816406c-149.11962891 0-270.03251953-120.91289062-270.03251953-270.03339843 0-149.11962891 120.91289062-270.03251953 270.03251953-270.03251954 149.12050781 0 270.03339844 120.91289062 270.03339844 270.03251954-0.16523437 149.28574219-121.078125 270.03339844-270.03339844 270.03339843z m0 0"  ></path><path d="M826.90068359 512c0 74.56025391-60.5390625 134.93408203-134.93408203 134.93408203S556.86816406 586.39501953 556.86816406 512c0-74.56025391 60.5390625-134.93408203 134.93408203-134.93408203 74.72460938-0.16523437 135.0984375 60.37382813 135.0984375 134.93408203z m0 0"  ></path></symbol><symbol id="icon-tag" viewBox="0 0 1024 1024"><path d="M962.8 605.1c0-1.2-0.1-2.3-0.2-3.5-0.5-5.8-1.8-11.6-3.7-17.2-2.3-6.7-5.6-13.2-10-19.2-2.2-3-4.6-5.9-7.3-8.6L463.2 73.8c-2.8-2.9-6-5.4-9.4-7.4-7.5-4.5-16.1-7-25.1-7H138.1c-41.2 0-74.6 33-74.6 73.8v298.2c0 3.2 0.3 6.3 0.9 9.3 0.2 0.8 0.3 1.5 0.5 2.3 0.6 2.3 1.3 4.5 2.2 6.6 0.3 0.7 0.6 1.4 1 2.1 2.3 4.9 5.5 9.4 9.5 13.4l238.9 236 238.9 236c5.5 5.4 11.6 9.8 18.1 13.2 10.8 5.6 22.8 8.4 34.7 8.4 9.6 0 19.1-1.8 28.1-5.4 9-3.6 17.4-9 24.7-16.2l280-276.8c1.7-1.7 3.3-3.4 4.8-5.2 2-2.4 3.8-4.9 5.4-7.4 1.1-1.7 2.1-3.4 3-5.1 0.9-1.7 1.7-3.3 2.4-5 0.4-0.9 0.8-1.8 1.1-2.7 3.9-9.6 5.5-19.7 5.1-29.8zM264.4 335.6c-39.3 0-71.2-31.9-71.2-71.2s31.9-71.2 71.2-71.2 71.2 31.9 71.2 71.2-31.9 71.2-71.2 71.2z" fill="" ></path></symbol></svg>';var script=function(){var scripts=document.getElementsByTagName("script");return scripts[scripts.length-1]}();var shouldInjectCss=script.getAttribute("data-injectcss");var ready=function(fn){if(document.addEventListener){if(~["complete","loaded","interactive"].indexOf(document.readyState)){setTimeout(fn,0)}else{var loadFn=function(){document.removeEventListener("DOMContentLoaded",loadFn,false);fn()};document.addEventListener("DOMContentLoaded",loadFn,false)}}else if(document.attachEvent){IEContentLoaded(window,fn)}function IEContentLoaded(w,fn){var d=w.document,done=false,init=function(){if(!done){done=true;fn()}};var polling=function(){try{d.documentElement.doScroll("left")}catch(e){setTimeout(polling,50);return}init()};polling();d.onreadystatechange=function(){if(d.readyState=="complete"){d.onreadystatechange=null;init()}}}};var before=function(el,target){target.parentNode.insertBefore(el,target)};var prepend=function(el,target){if(target.firstChild){before(el,target.firstChild)}else{target.appendChild(el)}};function appendSvg(){var div,svg;div=document.createElement("div");div.innerHTML=svgSprite;svgSprite=null;svg=div.getElementsByTagName("svg")[0];if(svg){svg.setAttribute("aria-hidden","true");svg.style.position="absolute";svg.style.width=0;svg.style.height=0;svg.style.overflow="hidden";prepend(svg,document.body)}}if(shouldInjectCss&&!window.__iconfont__svg__cssinject__){window.__iconfont__svg__cssinject__=true;try{document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>")}catch(e){console&&console.log(e)}}ready(appendSvg)})(window)