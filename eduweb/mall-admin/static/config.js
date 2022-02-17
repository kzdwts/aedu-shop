window.g = {
    AXIOS_TIMEOUT: 10000,
    http: "http://",
    downloadPlugUrl: "",
    ApiUrl: 'http://admin-changgou-java.itheima.net/api', // 配置服务器地址,
    // ImgUrl:'http://192.168.0.129:8888/',//服务器图片路径	
    //crawlUrl:'http://192.168.0.129:9999', // 数据采集服务器地址,
    crawlUrl: 'http://localhost:8088', // 本地数据采集服务器地址,
    port: "8888",
    ParentPage: {
        CrossDomainProxyUrl: '/Home/CrossDomainProxy',
        BtnsApi: '/api/services/app/Authorization/GetBtns',
        OrgsApi: '/api/services/app/authorization/GetOrgsByUserId'
    },
    targetLanguagelist: [
        { id: "zh-CHS", name: "中文" },
        { id: "EN", name: "英文" },
        { id: "ko", name: "韩文" },
        { id: "fr", name: "法文" },
        { id: "ar", name: "阿拉伯文" },
        { id: "ja", name: "日文" },
        { id: "pl", name: "波兰文" },
        { id: "da", name: "丹麦文" },
        { id: "de", name: "德文" },
        { id: "ru", name: "俄文" },
        { id: "fi", name: "芬兰文" },
        { id: "nl", name: "荷兰文" },
        { id: "cs", name: "捷克文" },
        { id: "ro", name: "罗马尼亚文" },
        { id: "no", name: "挪威文" },
        { id: "pt", name: "葡萄牙文" },
        { id: "sv", name: "瑞典文" },
        { id: "sk", name: "斯洛伐克文" },
        { id: "es", name: "西班牙文" },
        { id: "hi", name: "印地文" },
        { id: "id", name: "印度尼西亚文" },
        { id: "it", name: "意大利文" },
        { id: "th", name: "泰文" },
        { id: "tr", name: "土耳其文" },
        { id: "el", name: "希腊文" },
        { id: "hu", name: "匈牙利文" },
    ], //语言
}