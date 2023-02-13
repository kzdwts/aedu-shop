-- 引入json解析库
local cjson = require("cjson")
-- kafka库
local producer = require "resty.kafka.producer"
-- kafka的链接地址
local broker_list = {
    {
        host = "192.168.10.132",
        port = 9092
    }
}
-- 生产者
local pro = producer:new(broker_list, { producer_type = "async"})

-- 用户IP
local headers = ngx.req.get_headers()
local ip = headers["X-REAL-IP"] or headers["X_FORWARDED_FOR"] or ngx.var.remove_addr or "0.0.0.0"

-- 消息内容
local logjson = {}
logjson["uri"] = ngx.var.uri
logjson["ip"] = ip
logjson["accesstime"] = os.date("%Y-%M-%d %H:%m:%S")

-- 发送消息
local offset, err = pro:send("mslogs", nil, cjson.encode(logjson))

if not ok then
    ngx.log(ngx.ERR, "kafka send err:", err)
    return
end


