/**
 * @description锛氬皢JDF涓病鏈夊寘鍚殑CookieUtil锛屽崟鐙娊绂绘垚JS鏂囦欢
 * @author锛歝dwanchuan@jd.com 2015-08-25
*/

/**
 Cookie util
*/
var CookieUtil = {} ;
/**
 Add cookie using ascii encoder
 */
CookieUtil.setASCIICookie = function (sName, sValue, oExpires, sPath, sDomain, bSecure) {
	if ( "string" == typeof(sName) && "string" == typeof(sValue) ) {
		sValue = escape(sValue) ;
		CookieUtil.setCookie(sName, sValue, oExpires, sPath, sDomain, bSecure) ;
	}
} ;
/**
 Add cookie using unicode encoder
 */
CookieUtil.setUnicodeCookie = function (sName, sValue, oExpires, sPath, sDomain, bSecure) {
	if ( "string" == typeof(sName) && "string" == typeof(sValue) ) {
		sValue = encodeURIComponent(sValue) ;
		CookieUtil.setCookie(sName, sValue, oExpires, sPath, sDomain, bSecure) ;
	}
} ;
/**
 Add cookie
*/
CookieUtil.setCookie = function (sName, sValue, oExpires, sPath, sDomain, bSecure) {
	if ( "string" == typeof(sName) && "string" == typeof(sValue) ) {
		var sCookie = sName + "=" + sValue ;
		if (oExpires) {
			sCookie += "; expires=" + oExpires.toGMTString() ;
		}
		if (sPath) {
			sCookie += "; path=" + sPath ;
		}
		if (sDomain) {
			sCookie += "; domain=" + sDomain ;
		}
		if (bSecure) {
			sCookie += "; secure" ;
		}
		document.cookie = sCookie ;
	}
} ;
/**
 Get cookie value using Unicode decoder
 */
CookieUtil.getUnicodeCookie = function (sName) {
	var sValue = getCookie(sName) ;
	if ( "string" == typeof(sName) ) {
		var sEncVal = getCookie(sName) ;
		if (null == sEncVal) {
			return null ;
		} else {
			return decodeURIComponent(sEncVal) ;
		}
	} else {
		var sCookies = document.cookie ;
		return sCookies ;
	}
} ;
/**
 Get cookie value using ASCII decoder
 */
CookieUtil.getASCIICookie = function (sName) {
	var sValue = getCookie(sName) ;
	if ( "string" == typeof(sName) ) {
		var sEncVal = getCookie(sName) ;
		if (null == sEncVal) {
			return null ;
		} else {
			return unescape(sEncVal) ;
		}
	} else {
		var sCookies = document.cookie ;
		return sCookies ;
	}
} ;
/**
 Get original cookie value by name
*/
CookieUtil.getCookie = function (sName) {
	var sCookies = document.cookie ;
	if ("string" == typeof(sName)) {
		var sRE = "(?:; )?"+sName+"=([^;]*);?" ;
		var reRE = new RegExp(sRE) ;
		if (reRE.test(sCookies)) {
			return RegExp["$1"] ;
		} else {
			return null ;
		}
	} else {
		return sCookies ;
	}
} ;
/**
delete cookie
*/
CookieUtil.deleteCookie = function (sName, sPath, sDomain) {
	CookieUtil.setCookie(sName, "", new Date(0), sPath, sDomain) ;
} ;

function search(sNodeId) {
    var sUrl_T = "//search.jd.com/Search?keyword={keyword}&enc={enc}{additional}";
    var sAdditionalSearch = search.additinal || "";
    var oKeyTextN = document.getElementById(sNodeId);
    var sSelKeyValue = oKeyTextN.value;
    sSelKeyValue = sSelKeyValue.replace(/^\s*(.*?)\s*$/, "$1");
    if (sSelKeyValue.length > 100) {
        sSelKeyValue = sSelKeyValue.substring(0, 100);
    }
    if ("" == sSelKeyValue) {
        window.location.href = window.location.href;
        return;
    }
    var iSearchType = 0;
    if ("undefined" != typeof (window.pageConfig) && "undefined" != typeof (window.pageConfig.searchType)) {
        iSearchType = window.pageConfig.searchType;
    }
    var sCIdParamKey_T = "&cid{level}={cid}";
    var sCId = ("string" == typeof (search.cid) ? search.cid : "");
    var sCLevel = ("string" == typeof (search.cLevel) ? search.cLevel : "");
    var sEvVal = ("string" == typeof (search.ev_val) ? search.ev_val : "");
    switch (iSearchType) {
        case 0:
            break;
        case 1:
            sCLevel = "-1";
            sAdditionalSearch += "&book=y";
            break;
        case 2:
            sCLevel = "-1";
            sAdditionalSearch += "&mvd=music";
            break;
        case 3:
            sCLevel = "-1";
            sAdditionalSearch += "&mvd=movie";
            break;
        case 4:
            sCLevel = "-1";
            sAdditionalSearch += "&mvd=education";
            break;
        case 5:
            var OTHER_FILTERS_T = "&other_filters=%3Bcid1%2CL{cid1}M{cid1}[cid2]";
            switch (sCLevel) {
                case "51":
                    sCIdParamKey_T = OTHER_FILTERS_T.replace(/\[cid2]/, "");
                    sCIdParamKey_T = sCIdParamKey_T.replace(/\{cid1}/g, "5272");
                    break;
                case "52":
                    sCIdParamKey_T = OTHER_FILTERS_T.replace(/\{cid1}/g, "5272");
                    sCIdParamKey_T = sCIdParamKey_T.replace(/\[cid2]/, "%3Bcid2%2CL{cid}M{cid}");
                    break;
                case "61":
                    sCIdParamKey_T = OTHER_FILTERS_T.replace(/\[cid2]/, "");
                    sCIdParamKey_T = sCIdParamKey_T.replace(/\{cid1}/g, "5273");
                    break;
                case "62":
                    sCIdParamKey_T = OTHER_FILTERS_T.replace(/\{cid1}/g, "5273");
                    sCIdParamKey_T = sCIdParamKey_T.replace(/\[cid2]/, "%3Bcid2%2CL{cid}M{cid}");
                    break;
                case "71":
                    sCIdParamKey_T = OTHER_FILTERS_T.replace(/\[cid2]/, "");
                    sCIdParamKey_T = sCIdParamKey_T.replace(/\{cid1}/g, "5274");
                    break;
                case "72":
                    sCIdParamKey_T = OTHER_FILTERS_T.replace(/\{cid1}/g, "5274");
                    sCIdParamKey_T = sCIdParamKey_T.replace(/\[cid2]/, "%3Bcid2%2CL{cid}M{cid}");
                    break;
                case "81":
                    sCIdParamKey_T = OTHER_FILTERS_T.replace(/\[cid2]/, "");
                    sCIdParamKey_T = sCIdParamKey_T.replace(/\{cid1}/g, "5275");
                    break;
                case "82":
                    sCIdParamKey_T = OTHER_FILTERS_T.replace(/\{cid1}/g, "5275");
                    sCIdParamKey_T = sCIdParamKey_T.replace(/\[cid2]/, "%3Bcid2%2CL{cid}M{cid}");
                    break;
                default:
                    break;
            }
            sUrl_T = "//search.e.jd.com/searchDigitalBook?ajaxSearch=0&enc=utf-8&key={keyword}&page=1{additional}";
            break;
        case 6:
            sCLevel = "-1";
            sUrl_T = "//music.jd.com/8_0_desc_0_0_1_15.html?key={keyword}";
            break;
        case 7:
        	sUrl_T = "//s.e.jd.com/Search?key={keyword}&enc=utf-8";
        	break;
        default:
            break;
    }
    if ("string" == typeof (sCId) && "" != sCId && "string" == typeof (sCLevel)) {
        var reLevel = /^(?:[1-8])?([1-3])$/;
        if ("-1" == sCLevel) {
            sCLevel = "";
        } else {
            if (reLevel.test(sCLevel)) {
                sCLevel = RegExp.$1;
            } else {
                sCLevel = "";
            }
        }
        var sCidParam = sCIdParamKey_T.replace(/\{level}/, sCLevel);
        sCidParam = sCidParam.replace(/\{cid}/g, sCId);
        sAdditionalSearch += sCidParam;
    }
    if ("string" == typeof (sEvVal) && "" != sEvVal) {
        sAdditionalSearch += "&ev=" + sEvVal;
    }
    sSelKeyValue = encodeURIComponent(sSelKeyValue);
    sUrl = sUrl_T.replace(/\{keyword}/, sSelKeyValue);
    sUrl = sUrl.replace(/\{enc}/, 'utf-8');
    sUrl = sUrl.replace(/\{additional}/, sAdditionalSearch);
    if ("object" === typeof $o) {
    	if ("string" === typeof $o.lastKeyword) {
    		sUrl += '&wq=' + encodeURIComponent($o.lastKeyword);
    	}
    	if ("string" === typeof $o.pvid) {
    		sUrl += '&pvid=' + $o.pvid;
    	}
    }
    if ("undefined" == typeof (search.isSubmitted) || false == search.isSubmitted) {
        setTimeout(function() {
            window.location.href = sUrl;
        }, 10);
        search.isSubmitted = true;
    }
}

/**
 * localStorage鎿嶄綔鏂规硶
 * */
var Storage = (function(window) {
    var localStorage = window.localStorage;

    // 娴忚鍣ㄤ笉鏀寔localStorage
    if (!window.localStorage) {
        localStorage.setItem = function () {};
        localStorage.removeItem = function () {};
        localStorage.getItem = function () { return ''; }
    }

    var Storage = function(scope) {
        return new Storage.fn.init(scope);
    };

    Storage.fn = Storage.prototype = {
        constructor: Storage,
        init: function(scope) {
            var self = this;
            self.scope = scope;
        },
        getItem: function (key) {
            try {
                var result = JSON.parse(localStorage.getItem(this.scope + key));
                // 濡傛灉杩欎釜鍊兼病鏈夎繃鏈燂紝鎴栬€呮病鏈夎缃繃鏈熸椂闂达紝閭ｄ箞灏辫繑鍥炶繖涓€�
                if ((result.expires && (new Date().getTime() < result.expires)) || result.expires === undefined) {
                    return result.val;
                }
                return null;
            } catch (e) {
                return null;
            }
        },
        setItem: function (key, value, expires) {
            var data = {};
            data.val = value;
            if (expires) {
                data.expires = new Date().getTime() + expires;
            }
            localStorage.setItem(this.scope + key, JSON.stringify(data));
        },
        removeItem: function (key) {
            localStorage.removeItem(key);
        },
        clear: function () {
            var removeList = [];
            for (var key in localStorage) {
                if (key.indexOf(this.scope) === 0) {
                    removeList.push(key);
                }
            }
            $.each(removeList, function (index, item) {
                localStorage.removeItem(item);
            });
        }
    };

    Storage.fn.init.prototype = Storage.fn;

    return Storage;
})(window);
/*
 * @function锛氬幓闄isc寮曞叆鐨刲ib-v1.js锛岃€孞DF鍩虹搴揵ase.js骞舵病鏈夊寘鍚玪ib-v1.js涓殑鏂规硶
 * @description锛� NotifyPo锛� jshop.lib-v1.js  module\Module.js
 * @description锛� TrimPath锛� jshop.lib-v1.js  module\Module.js
 * @description 锛�$.fn.jdThickBox锛� jshop.lib-v1.js edit\share.js  ifollow\ifollow.js  sale-attention\saleFollow.js  shop-attention\shopFollow.js
 * @description锛� jdThickBoxclose锛� jshop.lib-v1.js  sale-attention\saleFollow.js
 * @author锛�20150826 cdwanchuan@jd.com
 */

// jdModelCallCenter: Object  //姝ｅ湪浣跨敤姝ゆ柟娉曠殑鏂囦欢 module\utils.js jshop.lib-v1.js  ifollow\ifollow.js  lottery\lottery.js  shop-attention\shopFollow.js  jshop-vote\jshopVote.js  module\Module.js    des\module.min.js  des\base.tools.min.js des\modules.src.js des\base.tools.src.js
//$.login() //jQuery涓婃墿灞曠殑鐧诲綍鏂规硶      //姝ｅ湪浣跨敤姝ゆ柟娉曠殑鏂囦欢 lottery\lottery.js


/**
* @lib-v1
* @update 2015-09-17 10:16
* @!!!澶囧繕闇€瑕佸鐞嗙殑浜嬫儏鍜宐ug
* @1[宸茶В鍐砞 鍥句功棰戦亾鐢ㄥ埌浜唒ageConfig.FN_StringFormat
*/

 /*
    livequery
*/
eval(function(p,a,c,k,e,r){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('(4($){$.R($.7,{3:4(c,b,d){9 e=2,q;5($.O(c))d=b,b=c,c=z;$.h($.3.j,4(i,a){5(e.8==a.8&&e.g==a.g&&c==a.m&&(!b||b.$6==a.7.$6)&&(!d||d.$6==a.o.$6))l(q=a)&&v});q=q||Y $.3(2.8,2.g,c,b,d);q.u=v;$.3.s(q.F);l 2},T:4(c,b,d){9 e=2;5($.O(c))d=b,b=c,c=z;$.h($.3.j,4(i,a){5(e.8==a.8&&e.g==a.g&&(!c||c==a.m)&&(!b||b.$6==a.7.$6)&&(!d||d.$6==a.o.$6)&&!2.u)$.3.y(a.F)});l 2}});$.3=4(e,c,a,b,d){2.8=e;2.g=c||S;2.m=a;2.7=b;2.o=d;2.t=[];2.u=v;2.F=$.3.j.K(2)-1;b.$6=b.$6||$.3.I++;5(d)d.$6=d.$6||$.3.I++;l 2};$.3.p={y:4(){9 b=2;5(2.m)2.t.16(2.m,2.7);E 5(2.o)2.t.h(4(i,a){b.o.x(a)});2.t=[];2.u=Q},s:4(){5(2.u)l;9 b=2;9 c=2.t,w=$(2.8,2.g),H=w.11(c);2.t=w;5(2.m){H.10(2.m,2.7);5(c.C>0)$.h(c,4(i,a){5($.B(a,w)<0)$.Z.P(a,b.m,b.7)})}E{H.h(4(){b.7.x(2)});5(2.o&&c.C>0)$.h(c,4(i,a){5($.B(a,w)<0)b.o.x(a)})}}};$.R($.3,{I:0,j:[],k:[],A:v,D:X,N:4(){5($.3.A&&$.3.k.C){9 a=$.3.k.C;W(a--)$.3.j[$.3.k.V()].s()}},U:4(){$.3.A=v},M:4(){$.3.A=Q;$.3.s()},L:4(){$.h(G,4(i,n){5(!$.7[n])l;9 a=$.7[n];$.7[n]=4(){9 r=a.x(2,G);$.3.s();l r}})},s:4(b){5(b!=z){5($.B(b,$.3.k)<0)$.3.k.K(b)}E $.h($.3.j,4(a){5($.B(a,$.3.k)<0)$.3.k.K(a)});5($.3.D)1j($.3.D);$.3.D=1i($.3.N,1h)},y:4(b){5(b!=z)$.3.j[b].y();E $.h($.3.j,4(a){$.3.j[a].y()})}});$.3.L(\'1g\',\'1f\',\'1e\',\'1b\',\'1a\',\'19\',\'18\',\'17\',\'1c\',\'15\',\'1d\',\'P\');$(4(){$.3.M()});9 f=$.p.J;$.p.J=4(a,c){9 r=f.x(2,G);5(a&&a.8)r.g=a.g,r.8=a.8;5(14 a==\'13\')r.g=c||S,r.8=a;l r};$.p.J.p=$.p})(12);',62,82,'||this|livequery|function|if|lqguid|fn|selector|var|||||||context|each||queries|queue|return|type||fn2|prototype|||run|elements|stopped|false|els|apply|stop|undefined|running|inArray|length|timeout|else|id|arguments|nEls|guid|init|push|registerPlugin|play|checkQueue|isFunction|remove|true|extend|document|expire|pause|shift|while|null|new|event|bind|not|jQuery|string|typeof|toggleClass|unbind|addClass|removeAttr|attr|wrap|before|removeClass|empty|after|prepend|append|20|setTimeout|clearTimeout'.split('|'),0,{}));

 // 閫氱煡寮瑰嚭灞�
var NotifyPop = {
    _saleNotify: '//skunotify.'+ pageConfig.FN_getDomain() +'/pricenotify.html?',
    _stockNotify: '//skunotify.'+ pageConfig.FN_getDomain() +'/storenotify.html?',
    init: function($btn, skuAttr) {
        var that = this,
            p = this.serializeUrl(location.href),
            query = /from=weibo/.test(location.href)? location.search.replace(/\?/, '') : '',
            type;

        // 寰崥callback url
        if ( /from=weibo/.test(location.href) ) {
            type = p.param.type;
            this.setThickBox(type, query);
        }

        // 褰㈠<a href="" data-type="1" data-sku="1234456">鍒拌揣閫氱煡</a>鎸夐挳
        $btn.livequery('click', function() {
            //btn-notice
            var _this = this,
                id = $(this).attr('id'),
                type = $(this).attr('data-type') || 2;

            that.sku = $(this).attr('data-sku');

            that.checkLogin(function(r) {
                if (!r.IsAuthenticated) {
                   thick_login(function(d){
						that._userPin = d.Identity.Name;
						that.setThickBox(type, query);
					});
                } else {
                    that._userPin = r.Name;
                    that.setThickBox(type, query);
                }
            });
            return false;
        }).attr('href', '#none').removeAttr('target');
    },
    serializeUrl: function(url) {
        var sep = url.indexOf('?'),
            link = url.substr( 0, sep),
            params = url.substr( sep+1 ),
            paramArr = params.split('&'),
            len = paramArr.length,i,
            res = {},curr,key,val;

        for ( i=0; i<len; i++) {
            curr = paramArr[i].split('=');
            key = curr[0];
            val = curr[1];

            res[key] = val;
        }

        return {
            url: link,
            param: res
        }
    },
    checkLogin: function(cb) {
        if ( typeof cb !== 'function' ) { return; }

        $.getJSON('//passport.' + pageConfig.FN_getDomain() + '/loginservice.aspx?method=Login&callback=?', function(r) {
            if ( r.Identity ) {
                cb(r.Identity);
            }
        });
    },
    setThickBox: function(type, query) {
        //webSite=1&origin=1&source=1
        var title,url, w, h,
            param = {
                skuId: this.sku,
                pin: this._userPin,
                webSite: 1,
                origin: 1,
                source: 1
            },
            p = this.serializeUrl(location.href);

        if ( /blogPin/.test(location.href) ) {
            param.blogPin = p.param.blogPin;
        }

        if ( type == 1 ) {
            title = '闄嶄环閫氱煡';
            url = this._saleNotify;
            h = 250;
        }
        if ( type == 2 ) {
            title = '鍒拌揣閫氱煡';
            url = this._stockNotify;
            h = 210;
            param.storeAddressId = readCookie('ipLoc-djd') || '0-0-0';
        }

        if ( !!query ) {
            url = url + query;
        } else {
            url = url + $.param(param);
        }
        $.jdThickBox({
            type: 'iframe',
            source: decodeURIComponent(url) + '&nocache=' + (+new Date()),
            width: 500,
            height: h,
            title: title,
            _box: "notify_box",
            _con: "notify_con",
            _title: "notify_title"
        });
    }
};

/*
    trimpath
*/
var TrimPath;
(function() {
    if (TrimPath == null) TrimPath = new Object();
    if (TrimPath.evalEx == null) TrimPath.evalEx = function(src) {
        return eval(src);
    };
    var UNDEFINED;
    if (Array.prototype.pop == null) Array.prototype.pop = function() {
        if (this.length === 0) {
            return UNDEFINED;
        }
        return this[--this.length];
    };
    if (Array.prototype.push == null) Array.prototype.push = function() {
        for (var i = 0; i < arguments.length; ++i) {
            this[this.length] = arguments[i];
        }
        return this.length;
    };
    TrimPath.parseTemplate = function(tmplContent, optTmplName, optEtc) {
        if (optEtc == null) optEtc = TrimPath.parseTemplate_etc;
        var funcSrc = parse(tmplContent, optTmplName, optEtc);
        var func = TrimPath.evalEx(funcSrc, optTmplName, 1);
        if (func != null) return new optEtc.Template(optTmplName, tmplContent, funcSrc, func, optEtc);
        return null;
    }
    try {
        String.prototype.process = function(context, optFlags) {
            var template = TrimPath.parseTemplate(this, null);
            if (template != null) return template.process(context, optFlags);
            return this;
        }
    } catch (e) {}
    TrimPath.parseTemplate_etc = {};
    TrimPath.parseTemplate_etc.statementTag = "forelse|for|if|elseif|else|var|macro";
    TrimPath.parseTemplate_etc.statementDef = {
        "if": {
            delta: 1,
            prefix: "if (",
            suffix: ") {",
            paramMin: 1
        },
        "else": {
            delta: 0,
            prefix: "} else {"
        },
        "elseif": {
            delta: 0,
            prefix: "} else if (",
            suffix: ") {",
            paramDefault: "true"
        },
        "/if": {
            delta: -1,
            prefix: "}"
        },
        "for": {
            delta: 1,
            paramMin: 3,
            prefixFunc: function(stmtParts, state, tmplName, etc) {
                if (stmtParts[2] != "in") throw new etc.ParseError(tmplName, state.line, "bad for loop statement: " + stmtParts.join(' '));
                var iterVar = stmtParts[1];
                var listVar = "__LIST__" + iterVar;
                return ["var ", listVar, " = ", stmtParts[3], ";", "var __LENGTH_STACK__;", "if (typeof(__LENGTH_STACK__) == 'undefined' || !__LENGTH_STACK__.length) __LENGTH_STACK__ = new Array();", "__LENGTH_STACK__[__LENGTH_STACK__.length] = 0;", "if ((", listVar, ") != null) { ", "var ", iterVar, "_ct = 0;", "for (var ", iterVar, "_index in ", listVar, ") { ", iterVar, "_ct++;", "if (typeof(", listVar, "[", iterVar, "_index]) == 'function') {continue;}", "__LENGTH_STACK__[__LENGTH_STACK__.length - 1]++;", "var ", iterVar, " = ", listVar, "[", iterVar, "_index];"].join("");
            }
        },
        "forelse": {
            delta: 0,
            prefix: "} } if (__LENGTH_STACK__[__LENGTH_STACK__.length - 1] == 0) { if (",
            suffix: ") {",
            paramDefault: "true"
        },
        "/for": {
            delta: -1,
            prefix: "} }; delete __LENGTH_STACK__[__LENGTH_STACK__.length - 1];"
        },
        "var": {
            delta: 0,
            prefix: "var ",
            suffix: ";"
        },
        "macro": {
            delta: 1,
            prefixFunc: function(stmtParts, state, tmplName, etc) {
                var macroName = stmtParts[1].split('(')[0];
                return ["var ", macroName, " = function", stmtParts.slice(1).join(' ').substring(macroName.length), "{ var _OUT_arr = []; var _OUT = { write: function(m) { if (m) _OUT_arr.push(m); } }; "].join('');
            }
        },
        "/macro": {
            delta: -1,
            prefix: " return _OUT_arr.join(''); };"
        }
    }
    TrimPath.parseTemplate_etc.modifierDef = {
        "eat": function(v) {
            return "";
        },
        "escape": function(s) {
            return String(s).replace(/&/g, "&").replace(/</g, "<").replace(/>/g, ">");
        },
        "capitalize": function(s) {
            return String(s).toUpperCase();
        },
        "default": function(s, d) {
            return s != null ? s : d;
        }
    }
    TrimPath.parseTemplate_etc.modifierDef.h = TrimPath.parseTemplate_etc.modifierDef.escape;
    TrimPath.parseTemplate_etc.Template = function(tmplName, tmplContent, funcSrc, func, etc) {
        this.process = function(context, flags) {
            if (context == null) context = {};
            if (context._MODIFIERS == null) context._MODIFIERS = {};
            if (context.defined == null) context.defined = function(str) {
                return (context[str] != undefined);
            };
            for (var k in etc.modifierDef) {
                if (context._MODIFIERS[k] == null) context._MODIFIERS[k] = etc.modifierDef[k];
            }
            if (flags == null) flags = {};
            var resultArr = [];
            var resultOut = {
                write: function(m) {
                    resultArr.push(m);
                }
            };
            try {
                func(resultOut, context, flags);
            } catch (e) {
                if (flags.throwExceptions == true) throw e;
                var result = new String(resultArr.join("") + "[ERROR: " + e.toString() + (e.message ? '; ' + e.message : '') + "]");
                result["exception"] = e;
                return result;
            }
            return resultArr.join("");
        }
        this.name = tmplName;
        this.source = tmplContent;
        this.sourceFunc = funcSrc;
        this.toString = function() {
            return "TrimPath.Template [" + tmplName + "]";
        }
    }
    TrimPath.parseTemplate_etc.ParseError = function(name, line, message) {
        this.name = name;
        this.line = line;
        this.message = message;
    }
    TrimPath.parseTemplate_etc.ParseError.prototype.toString = function() {
        return ("TrimPath template ParseError in " + this.name + ": line " + this.line + ", " + this.message);
    }
    var parse = function(body, tmplName, etc) {
        body = cleanWhiteSpace(body);
        var funcText = ["var TrimPath_Template_TEMP = function(_OUT, _CONTEXT, _FLAGS) { with (_CONTEXT) {"];
        var state = {
            stack: [],
            line: 1
        };
        var endStmtPrev = -1;
        while (endStmtPrev + 1 < body.length) {
            var begStmt = endStmtPrev;
            begStmt = body.indexOf("{", begStmt + 1);
            while (begStmt >= 0) {
                var endStmt = body.indexOf('}', begStmt + 1);
                var stmt = body.substring(begStmt, endStmt);
                var blockrx = stmt.match(/^\{(cdata|minify|eval)/);
                if (blockrx) {
                    var blockType = blockrx[1];
                    var blockMarkerBeg = begStmt + blockType.length + 1;
                    var blockMarkerEnd = body.indexOf('}', blockMarkerBeg);
                    if (blockMarkerEnd >= 0) {
                        var blockMarker;
                        if (blockMarkerEnd - blockMarkerBeg <= 0) {
                            blockMarker = "{/" + blockType + "}";
                        } else {
                            blockMarker = body.substring(blockMarkerBeg + 1, blockMarkerEnd);
                        }
                        var blockEnd = body.indexOf(blockMarker, blockMarkerEnd + 1);
                        if (blockEnd >= 0) {
                            emitSectionText(body.substring(endStmtPrev + 1, begStmt), funcText);
                            var blockText = body.substring(blockMarkerEnd + 1, blockEnd);
                            if (blockType == 'cdata') {
                                emitText(blockText, funcText);
                            } else if (blockType == 'minify') {
                                emitText(scrubWhiteSpace(blockText), funcText);
                            } else if (blockType == 'eval') {
                                if (blockText != null && blockText.length > 0) funcText.push('_OUT.write( (function() { ' + blockText + ' })() );');
                            }
                            begStmt = endStmtPrev = blockEnd + blockMarker.length - 1;
                        }
                    }
                } else if (body.charAt(begStmt - 1) != '$' && body.charAt(begStmt - 1) != '\\') {
                    var offset = (body.charAt(begStmt + 1) == '/' ? 2 : 1);
                    if (body.substring(begStmt + offset, begStmt + 10 + offset).search(TrimPath.parseTemplate_etc.statementTag) == 0) break;
                }
                begStmt = body.indexOf("{", begStmt + 1);
            }
            if (begStmt < 0) break;
            var endStmt = body.indexOf("}", begStmt + 1);
            if (endStmt < 0) break;
            emitSectionText(body.substring(endStmtPrev + 1, begStmt), funcText);
            emitStatement(body.substring(begStmt, endStmt + 1), state, funcText, tmplName, etc);
            endStmtPrev = endStmt;
        }
        emitSectionText(body.substring(endStmtPrev + 1), funcText);
        if (state.stack.length != 0) throw new etc.ParseError(tmplName, state.line, "unclosed, unmatched statement(s): " + state.stack.join(","));
        funcText.push("}}; TrimPath_Template_TEMP");
        return funcText.join("");
    }
    var emitStatement = function(stmtStr, state, funcText, tmplName, etc) {
        var parts = stmtStr.slice(1, -1).split(' ');
        var stmt = etc.statementDef[parts[0]];
        if (stmt == null) {
            emitSectionText(stmtStr, funcText);
            return;
        }
        if (stmt.delta < 0) {
            if (state.stack.length <= 0) throw new etc.ParseError(tmplName, state.line, "close tag does not match any previous statement: " + stmtStr);
            state.stack.pop();
        }
        if (stmt.delta > 0) state.stack.push(stmtStr);
        if (stmt.paramMin != null && stmt.paramMin >= parts.length) throw new etc.ParseError(tmplName, state.line, "statement needs more parameters: " + stmtStr);
        if (stmt.prefixFunc != null) funcText.push(stmt.prefixFunc(parts, state, tmplName, etc));
        else funcText.push(stmt.prefix);
        if (stmt.suffix != null) {
            if (parts.length <= 1) {
                if (stmt.paramDefault != null) funcText.push(stmt.paramDefault);
            } else {
                for (var i = 1; i < parts.length; i++) {
                    if (i > 1) funcText.push(' ');
                    funcText.push(parts[i]);
                }
            }
            funcText.push(stmt.suffix);
        }
    }
    var emitSectionText = function(text, funcText) {
        if (text.length <= 0) return;
        var nlPrefix = 0;
        var nlSuffix = text.length - 1;
        while (nlPrefix < text.length && (text.charAt(nlPrefix) == '\n'))
        nlPrefix++;
        while (nlSuffix >= 0 && (text.charAt(nlSuffix) == ' ' || text.charAt(nlSuffix) == '\t'))
        nlSuffix--;
        if (nlSuffix < nlPrefix) nlSuffix = nlPrefix;
        if (nlPrefix > 0) {
            funcText.push('if (_FLAGS.keepWhitespace == true) _OUT.write("');
            var s = text.substring(0, nlPrefix).replace('\n', '\\n');
            if (s.charAt(s.length - 1) == '\n') s = s.substring(0, s.length - 1);
            funcText.push(s);
            funcText.push('");');
        }
        var lines = text.substring(nlPrefix, nlSuffix + 1).split('\n');
        for (var i = 0; i < lines.length; i++) {
            emitSectionTextLine(lines[i], funcText);
            if (i < lines.length - 1) funcText.push('_OUT.write("\\n");\n');
        }
        if (nlSuffix + 1 < text.length) {
            funcText.push('if (_FLAGS.keepWhitespace == true) _OUT.write("');
            var s = text.substring(nlSuffix + 1).replace('\n', '\\n');
            if (s.charAt(s.length - 1) == '\n') s = s.substring(0, s.length - 1);
            funcText.push(s);
            funcText.push('");');
        }
    }
    var emitSectionTextLine = function(line, funcText) {
        var endMarkPrev = '}';
        var endExprPrev = -1;
        while (endExprPrev + endMarkPrev.length < line.length) {
            var begMark = "${",
                endMark = "}";
            var begExpr = line.indexOf(begMark, endExprPrev + endMarkPrev.length);
            if (begExpr < 0) break;
            if (line.charAt(begExpr + 2) == '%') {
                begMark = "${%";
                endMark = "%}";
            }
            var endExpr = line.indexOf(endMark, begExpr + begMark.length);
            if (endExpr < 0) break;
            emitText(line.substring(endExprPrev + endMarkPrev.length, begExpr), funcText);
            var exprArr = line.substring(begExpr + begMark.length, endExpr).replace(/\|\|/g, "#@@#").split('|');
            for (var k in exprArr) {
                if (exprArr[k].replace) exprArr[k] = exprArr[k].replace(/#@@#/g, '||');
            }
            funcText.push('_OUT.write(');
            emitExpression(exprArr, exprArr.length - 1, funcText);
            funcText.push(');');
            endExprPrev = endExpr;
            endMarkPrev = endMark;
        }
        emitText(line.substring(endExprPrev + endMarkPrev.length), funcText);
    }
    var emitText = function(text, funcText) {
        if (text == null || text.length <= 0) return;
        text = text.replace(/\\/g, '\\\\');
        text = text.replace(/\n/g, '\\n');
        text = text.replace(/"/g, '\\"');
        funcText.push('_OUT.write("');
        funcText.push(text);
        funcText.push('");');
    }
    var emitExpression = function(exprArr, index, funcText) {
        var expr = exprArr[index];
        if (index <= 0) {
            funcText.push(expr);
            return;
        }
        var parts = expr.split(':');
        funcText.push('_MODIFIERS["');
        funcText.push(parts[0]);
        funcText.push('"](');
        emitExpression(exprArr, index - 1, funcText);
        if (parts.length > 1) {
            funcText.push(',');
            funcText.push(parts[1]);
        }
        funcText.push(')');
    }
    var cleanWhiteSpace = function(result) {
        result = result.replace(/\t/g, "    ");
        result = result.replace(/\r\n/g, "\n");
        result = result.replace(/\r/g, "\n");
        result = result.replace(/^(\s*\S*(\s+\S+)*)\s*$/, '$1');
        return result;
    }
    var scrubWhiteSpace = function(result) {
        result = result.replace(/^\s+/g, "");
        result = result.replace(/\s+$/g, "");
        result = result.replace(/\s+/g, " ");
        result = result.replace(/^(\s*\S*(\s+\S+)*)\s*$/, '$1');
        return result;
    }
    TrimPath.parseDOMTemplate = function(elementId, optDocument, optEtc) {
        if (optDocument == null) optDocument = document;
        var element = optDocument.getElementById(elementId);
        var content = element.value;
        if (content == null) content = element.innerHTML;
        content = content.replace(/</g, "<").replace(/>/g, ">");
        return TrimPath.parseTemplate(content, elementId, optEtc);
    }
    TrimPath.processDOMTemplate = function(elementId, context, optFlags, optDocument, optEtc) {
        return TrimPath.parseDOMTemplate(elementId, optDocument, optEtc).process(context, optFlags);
    }
})();

/*
    jdThickbox
*/
(function($) {
    $.extend($.browser, {
        client: function() {
            return {
                width: document.documentElement.clientWidth,
                height: document.documentElement.clientHeight,
                bodyWidth: document.body.clientWidth,
                bodyHeight: document.body.clientHeight
            };
        },
        scroll: function() {
            var dds = document.documentElement.scrollTop;
            var dbs = document.body.scrollTop;
            var ddl = document.documentElement.scrollLeft;
            var dbl = document.body.scrollLeft;
            var top = !!dds ? dds : dbs;
            var left = !!ddl ? ddl : dbl;

            return {
                width: document.documentElement.scrollWidth,
                height: document.documentElement.scrollHeight,
                bodyWidth: document.body.scrollWidth,
                bodyHeight: document.body.scrollHeight,
                left: left,
                top: top
            };
        },
        screen: function() {
            return {
                width: window.screen.width,
                height: window.screen.height
            };
        },
        isIE6: $.browser && $.browser.msie && $.browser.version == 6,
        isMinW: function(val) {
            return Math.min($.browser.client().bodyWidth, $.browser.client().width) <= val;
        },
        isMinH: function(val) {
            return $.browser.client().height <= val;
        }
    })
})(jQuery);
(function($) {
    $.fn.jdPosition = function(option) {
        var s = $.extend({
            mode: null
        }, option || {});
        switch (s.mode) {
        default:
        case "center":
            var ow = $(this).outerWidth(),
                oh = $(this).outerHeight();
            var w = $.browser.isMinW(ow),
                h = $.browser.isMinH(oh);

            $(this).css({
                left: $.browser.scroll().left + Math.max(($.browser.client().width - ow) / 2, 0) + "px",
                top: (!$.browser.isIE6)
                    ? (h ? $.browser.scroll().top : ($.browser.scroll().top + Math.max(($.browser.client().height - oh) / 2, 0) + "px"))
                    : (($.browser.scroll().top <= $.browser.client().bodyHeight - oh)
                        ? ($.browser.scroll().top + Math.max(($.browser.client().height - oh) / 2, 0) + "px")
                        : ($.browser.client().height - oh)/2 + "px")
            });
            break;
        case "auto":
            break;
        case "fixed":
            break
        }
    }
})(jQuery);


(function($) {
    $.fn.jdThickBox = function(option, callback) {
        if (typeof option == "function") {
            callback = option;
            option = {}
        };
        var s = $.extend({
            type: "text",
            source: null,
            width: null,
            height: null,
            title: null,
            _frame: "",
            _div: "",
            _box: "",
            _con: "",
            _loading: "thickloading",
            close: false,
            _close: "",
            _fastClose: false,
            _close_val: "脳",
            _titleOn: true,
            _title: "",
            _autoReposi: false,
            _countdown: false,
            _thickPadding: 10,
            _wrapBorder:1
        }, option || {});
        var object = (typeof this != "function") ? $(this) : null;
        var timer;
        var close = function() {
            clearInterval(timer);
            $(".thickframe").add(".thickdiv").remove();
            $(".thickbox").empty().remove();
            if (s._autoReposi) {
                $(window).unbind("resize.jdThickBox").unbind("scroll.jdThickBox")
            }
        };
        if (s.close) {
            close();
            return false
        };
        var reg = function(str) {
            if (str != "") {
                return str.match(/\w+/)
            } else {
                return ""
            }
        };
        var init = function(element) {
            if ($(".thickframe").length == 0 || $(".thickdiv").length == 0) {
                $("<iframe class='thickframe' id='" + reg(s._frame) + "' marginwidth='0' marginheight='0' frameborder='0' scrolling='no'></iframe>").appendTo($(document.body));
                $("<div class='thickdiv' id='" + reg(s._div) + "'></div>").appendTo($(document.body))
            } else {
                $(".thickframe").add(".thickdiv").show()
            };
            $("<div class='thickbox' id='" + reg(s._box) + "'><div class='thickwrap'></div></div>").appendTo($(document.body));

            if ( $('.thickwrap') ) {
                $('.thickwrap').css('width', s.width + s._thickPadding*2 );
                s._wrapBorder = 1;
            }
            if (s._titleOn) initTitle(element);
            $("<div class='thickcon' id='" + reg(s._con) + "' style='width:" + s.width + "px;height:" + s.height + "px;'></div>").appendTo($(".thickwrap"));
            if (s._countdown) initCountdown();
            $(".thickcon").addClass(s._loading);

            reposi();
            initClose();
            inputData(element);
            if (s._autoReposi) {
                $(window).bind("resize.jdThickBox", reposi).bind("scroll.jdThickBox", reposi)
            };
            if (s._fastClose) {
                $(document.body).bind("click.jdThickBox", function(e) {
                    e = e ? e : window.event;
                    var tag = e.srcElement ? e.srcElement : e.target;
                    if (tag.className == "thickdiv") {
                        $(this).unbind("click.jdThickBox");
                        close()
                    }
                })
            }
        };
        var initCountdown = function() {
            var x = s._countdown;
            $("<div class='thickcountdown' style='width:" + s.width + "'><span id='jd-countdown'>" + x + "</span>绉掑悗鑷姩鍏抽棴</div>").appendTo($(".thickwrap"));
            timer = setInterval(function() {
                x--;
                $("#jd-countdown").html(x);
                if (x == 0) {
                    x = s._countdown;
                    close()
                }
            }, 1000)
        };
        var initTitle = function(element) {
            s.title = (s.title == null && element) ? element.attr("title") : s.title;
            $("<div class='thicktitle' id='" + reg(s._title) + "' style='width:" + s.width + "'><span>" + s.title + "</span></div>").appendTo($(".thickwrap"))
        };
        var initClose = function() {
            if (s._close != null) {
                $("<a href='#' class='thickclose' id='" + reg(s._close) + "'>" + s._close_val + "</a>").appendTo($(".thickwrap"));
                $(".thickclose").one("click", function() {
                    close();
                    return false
                })
            }
        };
        var inputData = function(element) {
            s.source = (s.source == null) ? element.attr("href") : s.source;
            switch (s.type) {
            default:
            case "text":
                $(".thickcon").html(s.source);
                $(".thickcon").removeClass(s._loading);
                if (callback) {
                    callback()
                };
                break;
            case "html":
                $(s.source).clone().appendTo($(".thickcon")).show();
                $(".thickcon").removeClass(s._loading);
                if (callback) {
                    callback()
                };
                break;
            case "image":
                s._index = (s._index == null) ? object.index(element) : s._index;
                $(".thickcon").append("<img src='" + s.source + "' width='" + s.width + "' height='" + s.height + "'>");
                s.source = null;
                $(".thickcon").removeClass(s._loading);
                if (callback) {
                    callback()
                };
                break;
            case "ajax":
            case "json":
                if (callback) {
                    callback(s.source, $(".thickcon"), function() {
                        $(".thickcon").removeClass(s._loading)
                    })
                };
                break;
            case "iframe":
                $("<iframe src='" + s.source + "' marginwidth='0' marginheight='0' frameborder='0' scrolling='no' style='width:" + s.width + "px;height:" + s.height + "px;border:0;'></iframe>").appendTo($(".thickcon"));
                $(".thickcon").removeClass(s._loading);
                if (callback) {
                    callback()
                };
                break
            }
        };
        var reposi = function() {
            var w1 = s._thickPadding*2 + parseInt(s.width);

            $(".thickcon").css({
                width: s.width,
                height:s.height,
                paddingLeft: s._thickPadding,
                paddingRight: s._thickPadding
            });

            // 淇IE9璁＄畻 outerHeight 闂: .thickcon鍦↖E9涓繑鍥� undefined
            setTimeout(function() {
            $(".thickbox").css({
                    width: w1 + s._wrapBorder*2,
                    height: parseInt(s._titleOn ? $(".thicktitle").outerHeight() : 0) + parseInt($(".thickcon").outerHeight()) + s._wrapBorder*2
            });
            }, 100);

            $(".thickbox").jdPosition({
                mode: "center"
            });
            if ($.browser.isIE6) {
                var ow = $(".thickbox").outerWidth(),
                    oh = $(".thickbox").outerHeight();
                var w2 = $.browser.isMinW(ow),
                    h2 = $.browser.isMinH(oh);
                $(".thickframe").add(".thickdiv").css({
                    width: w2 ? ow : "100%",
                    height: Math.max($.browser.client().height, $.browser.client().bodyHeight) + "px"
                })
            }
        };
        if (object != null) {
            object.click(function() {
                init($(this));
                return false
            })
        } else {
            init()
        }
    };
    $.jdThickBox = $.fn.jdThickBox
})(jQuery);

function jdThickBoxclose() {
    $(".thickclose").trigger("click");
};

/**
* @鏍煎紡鍖栧瓧绗︿覆  20150917add by wanchuan
*/
pageConfig.FN_StringFormat=function(){
	var a = arguments[0],
		c = arguments.length;
	if (c > 0) {
		for (var b = 0; b < c; b++) {
			a = a.replace(new RegExp("\\{" + b + "\\}", "g"), arguments[b+1]);
		}
	}
	return a;
};/**
 * @function锛氭暟鎹ā鏉挎覆鏌�
 * @description锛歭oadMustache鏂规硶渚濊禆mustache.min.js锛屽叾浠栨槸鍙﹀涓€绉嶆ā鏉挎覆鏌�
 * @author锛歝dwanchuan@jd.com 2016-08-22
*/

(function(w, undefined){
    var Tool = {};
	/**
     * 鍘婚櫎瀛楃涓插墠鍚庣┖鏍煎瓧绗�
     * @param  {String} str 闇€瑕佸幓闄ゅ墠鍚庣┖鏍肩殑婧愬瓧绗︿覆
     * @return {String}     鍘婚櫎鍓嶅悗绌烘牸鍚庣殑鏂板瓧绗︿覆
     */
    Tool.trim = function(str) {
        return str.replace(/(^\s+)|(\s+$)/g, '');
    };
    /**
     * 鍓嶇妯℃澘娓叉煋鏂规硶锛屽皢json鏁版嵁鍔ㄦ€佹覆鏌撲负HTML缁撴瀯
     * @param  {[Object, Array, String, Number]} data 妯℃澘娓叉煋鎵€闇€鏁版嵁
     * @param  {String} template 妯℃澘瀛楃涓诧紝鍖呭惈妯℃澘璇硶
     * @return {String}          鏁村悎浜唈son鏁版嵁鐨凥TML瀹屾暣鐗囨
     */
    Tool.render = function(data, template) {
        var mod = null,
            funcBody = 'var breads=[];',
            index = 0,
            logic = '',
            rTpl = /{{(.*?)}}/g,
            sub = '';
        if (!data) {
            return '';
        }
        template = template.replace(/\n|\r/g, '');
        while (mod = rTpl.exec(template)) {
            sub = template.slice(index, mod.index).replace(/"/g, '\\"');
            if (sub) {
                funcBody += 'breads.push("' + sub + '");';
            }
            logic = this.trim(mod[1]);
            if (/^(var|if|for|switch|else|case|while|do|break|continue|\{|\}|\[|\])/.test(logic)) {
                funcBody += logic;
            } else {
                funcBody += 'breads.push(' + logic + ');';
            }
            index = mod.index + mod[0].length;
        }
        sub = template.slice(index).replace(/"/g, '\\"');
        if (sub) {
            funcBody += 'breads.push("' + sub + '");';
        }
        funcBody += 'return breads.join("");';
        return new Function('data', funcBody)(data);
    };
    /**
     * 鏍规嵁瑙勫垯鏍煎紡鍖杊ash鏁版嵁锛屽苟浠son鏍煎紡杩斿洖
     * @param  {String} rule 鎸囧畾鏍煎紡鍖栫殑瑙勫垯锛屼緥锛�/{type}/{page}/{sort}
     * @return {Object}      杩斿洖瑙勫垯鍚嶇О-hash鍊肩殑k-v鏁版嵁锛屼緥锛歿type:1,page:2:sort:"utd"}
     */
    Tool.parseHash = function(rule) {
        var rp = /[^{]+(?=\})/g,
            hash = (location.hash || '#').slice(1),
            qs = {},
            attrs = rule.match(rp),
            vs = hash.split('/');
        for (var i = 0; attrs[i]; i++) {
            qs[attrs[i]] = vs[i] || undefined;
        }
        return qs;
    };
    /**
     * 鏍煎紡鍖栨暟瀛楋紝涓綅鏁板墠缃�"0"
     * @param  {Number} num 闇€瑕佹牸寮忓寲鐨勬簮鏁板瓧
     * @return {[String, Number]} 鏍煎紡鍖栧悗鐨勬暟瀛楀瓧绗︿覆鎴栨簮澶氫綅鏁板瓧
     */
    Tool.parseNumber = function(num) {
        return num < 10 ? "0" + num : num;
    };
    /**
     * 鏃ユ湡鏃堕棿鏍煎紡鍖栨柟娉曪紝鏍规嵁鐗瑰畾鏍煎紡杩斿洖鎵€闇€鏍煎紡鐨勬椂闂村瓧绗︿覆
     * @param  {Date} date 鐢ㄤ簬鏍煎紡鍖栫殑鏃ユ湡鏁版嵁
     * @param  {String} formatStr 鏍煎紡鍖栬鍒欙紝渚嬶細"{Y}-{MM}-{DD} {hh}:{ii}:{ss}"
     * @return {String} 杩斿洖鎸囧畾鏍煎紡鐨勬椂闂村瓧绗︿覆
     */
    Tool.dateFormat = function(date, formatStr) {
        var dateObj = {},
            rStr = /\{([^}]{1,2})\}/;

        dateObj["Y"] = date.getFullYear();
        dateObj["M"] = date.getMonth() + 1;
        dateObj["MM"] = this.parseNumber(dateObj["M"]);
        dateObj["D"] = date.getDate();
        dateObj["DD"] = this.parseNumber(dateObj["D"]);
        dateObj["h"] = date.getHours();
        dateObj["hh"] = this.parseNumber(dateObj["h"]);
        dateObj["i"] = date.getMinutes();
        dateObj["ii"] = this.parseNumber(dateObj["i"]);
        dateObj["s"] = date.getSeconds();
        dateObj["ss"] = this.parseNumber(dateObj["s"]);

        while(rStr.test(formatStr)) {
            formatStr = formatStr.replace(rStr, dateObj[RegExp.$1]);
        }
        return formatStr;
    };
    /**
     * 灏唕gb(a)鏍煎紡棰滆壊鍊硷紝杞崲涓哄崄鍏繘鍒讹紙hex锛夋牸寮�
     * @param  {String} rgb rgb(a)鏍煎紡鐨勯鑹插€�
     * @return {Object}     鍖呭惈鍗佸叚杩涘埗鏍煎紡棰滆壊鍊煎拰Alpha閫忔槑搴︾殑JSON瀵硅薄
     */
    Tool.rgbToHex = function(rgb) {
        var rRgba = /rgba?\((\d{1,3}),(\d{1,3}),(\d{1,3})(,([.\d]+))?\)/,
            r, g, b, a,
            rsa = rgb.replace(/\s+/g, "").match(rRgba);
        if (rsa) {
            r = (+rsa[1]).toString(16);
            r = r.length == 1 ? "0" + r : r;
            g = (+rsa[2]).toString(16);
            g = g.length == 1 ? "0" + g : g;
            b = (+rsa[3]).toString(16);
            b = b.length == 1 ? "0" + b : b;
            a = (+(rsa[5] ? rsa[5] : 1)) * 100
            return {hex: "#" + r + g + b, alpha: Math.ceil(a)};
        } else {
            return {hex: rgb, alpha: 100};
        }
    };

    Tool.guid = function(prefix) {
        return (prefix + '_') + (new Date().getTime().toString(32));
    };

    /**
     * 灏嗛潪鏍囧噯JSON鏍煎紡鐨勫瓧绗︿覆鏍煎紡鍖栬緭鍑篔SON瀵硅薄
     * @param str 寰呮牸寮忓寲瀛楃涓�
     * @returns {Object} 鏍煎紡鍖栧悗鐨凧SON鏁版嵁
     */
    Tool.stringToJSONByEval = function(str) {
        return eval('(' + str + ')');
    };
	
	/**
     * @function锛氫娇鐢∕ustache妯℃澘寮曟搸
	 * @decsription锛氫緷璧朚ustache.js
     * @param锛� template锛氭ā鏉垮瓧绗︿覆锛沝ata锛氭暟鎹紱container锛氬～鍏呭鍣�
     * @Author锛歝dwanchuan@jd.com 20160818
     */
	Tool.loadMustache = function(template,data,container) {
	  Mustache.parse(template);
	  var rendered = Mustache.render(template, data);
	  
	  container.html(rendered);
	}

	w.Tool = Tool;
})(window);/**
 * @function锛氬師鍨嬫墿灞�
 * @description锛氬熀浜庡璞″師鍨嬫墿灞�
 * @author锛歝dwanchuan@jd.com 2016-08-22
*/

Array.prototype.each = function(fun){
	if(!fun.constructor == Function) return;
		for(var i = 0, len = this.length;i <len; i++) fun.call(this,i,this[i]);
};
Array.prototype.isIn = function(arg){
	for(var i = this.length; i > 0; i--){
		if(this[i-1] == arg)
			return true;
	}
	return false;
};
Array.prototype.isEmpty = function(){
    return this.length==0?true:false;
};

Array.prototype.index = function(item){
    for(var i = 0, len = this.length; i < len; i++){
        if(this[i] == item)
            return i;
    }
    return -1;
};
/**
 * By cdhewu: 鍏煎涓嶆敮鎸乮ndexOf鏂规硶鐨勬祻瑙堝櫒
 */
if (!Array.prototype.indexOf){
	Array.prototype.indexOf = function(elt /*, from*/){
		var len = this.length >>> 0;
		var from = Number(arguments[1]) || 0;
		from = (from < 0)
			? Math.ceil(from)
			: Math.floor(from);
		if (from < 0)
			from += len;
		for (; from < len; from++){
			if (from in this && this[from] === elt)
				return from;
		}
		return -1;
	};
}
Array.prototype.del= function(ele){
	  var index = this.getIndex(ele);
      if(index != -1){
            this.splice(index,1);
      }
      return this;
};

Array.prototype.aUnique = function(arr){
	if(arr.constructor != Array)
		throw new Error('Array.aUnique:arguments error!');
	var _result = [];
	for(var i = 0, len = arr.length; i < len; i++){
		if(!this.isIn(arr[i]))
			_result.push(arr[i]);
	}
	return _result;
};

//鏁扮粍鍘婚噸
Array.prototype.unique = function(){
	var res = [];
	var json = {};
	for(var i = 0; i < this.length; i++){
		if(!json[this[i]]){
			res.push(this[i]);
			json[this[i]] = 1;
		}
	}
	return res;
}

Array.prototype.getIndex = function(arg){
    var isUrl = typeof(arg) == "string" && (arg.indexOf("http://") == 0 || arg.indexOf("https://") == 0 || arg.indexOf("//") == 0);
	for(var i = 0, len = this.length; i < len; i++){
		var url = this[i];
        if(isUrl && typeof(url) == "string" && (url.indexOf("http://") == 0 || url.indexOf("https://") == 0 || url.indexOf("//") == 0)){
            url = url.replace(/^http(s)?:/, "");
            arg = arg.replace(/^http(s)?:/, "");
        }
		if(url == arg)
			return i;
	}
	return -1;
	
};
String.prototype.subDelete = function(startIndex,endIndex){
    if(!startIndex)
        throw new Error("subDelete is no-use!");
    if(!endIndex) return this.slice(startIndex);
    var str = this;
    return this.slice(0,startIndex).concat(this.slice(endIndex));
};
String.prototype.ltrim = function(){
    return this.replace(/(^s*)/g, "");
};
String.prototype.rtrim = function(){
    return this.replace(/(s*$)/g, "");
};
String.prototype.trim = function(){
    return this.rtrim(this.ltrim());
};

/**鍏ㄥ眬鏇挎崲瀛楃**/
String.prototype.replaceAll=function(oldStr,newStr){
    return this.replace(new RegExp(oldStr,"gm"),newStr);
};

Array.prototype.stringFormat = function(){
    for(var i =  0,len = this.length;i<len;i++){
        if(typeof this[i] == 'string'){
            this[i] = '"' + this[i] + '"';
        }else if(this[i] instanceof Array){
            this[i] = this[i].stringFormat();
        }else if(typeof this[i] == 'function'){
            this[i] = this[i];
        }
        else{
            this[i] = _jsonFormat(this[i]);
        }
    }
    return "["+this.join(",")+"]";

    function _jsonFormat(json){
        var substr = '{';
        for(var a in json){
            if(json.hasOwnProperty(a)){
                var pro = json[a];
                if(typeof pro == 'string'){
                    substr += a + ':"' + pro + '",';
                }
                else if(pro instanceof Array){
                    substr += a + ':' + pro.stringFormat() + ',';
                }
                else if(typeof pro == 'function'){
                    substr += a + ':' + pro + ',';
                }
                else{
                    substr += a + ':' + _jsonFormat(pro) + ',';
                }
            }
        }
        return substr.replace(/,$/,'') + '}';
    }
 };

/* 缁欐暟缁勫師鍨嬬粦瀹歟ach鏂规硶锛岀敤鍦╞anner澧炲姞鍜岀Щ闄ss */
Array.prototype.jBannerEach = function(fun){
    if(typeof fun != "function")
        throw new Error("Array:each argument error!");
    for(var i = 0,len = this.length;i<len;i++){
        fun.call(this,i,this[i]);
    }
};

String.prototype.getQuery = function(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"),
        r = this.substr(this.indexOf("\?")+1).match(reg);
    if (r!=null) return unescape(r[2]); return null;
};

String.prototype.trim = function(isGlobal){
	var result; 
	
	result = this.replace(/(^\s+)|(\s+$)/g,"");
	
	if(isGlobal){
		result = result.replace(/\s/g,"");
	}
	return result;
};

/**
 * 鍏煎NodeList鐨刦orEach
 */
if (window.NodeList && !NodeList.prototype.forEach) {
    NodeList.prototype.forEach = function (callback, thisArg) {
        thisArg = thisArg || window;
        for (var i = 0; i < this.length; i++) {
            callback.call(thisArg, this[i], i, this);
        }
    };
}