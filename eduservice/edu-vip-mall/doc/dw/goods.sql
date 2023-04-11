-- 查询每小时商品访问频率超过1w的商品
SELECT
    uri, count(*) AS viewCount
FROM
    mslogs
WHERE
    __time>=TIMESTAMP '2021-01-14 20:00:00'
    AND uri NOT IN ('/msitems/111.html', '/msitems/222.html')
GROUP BY
    uri
HAVING
    viewCount > 10000
ORDER BY
    viewCount DESC
LIMIT 100
