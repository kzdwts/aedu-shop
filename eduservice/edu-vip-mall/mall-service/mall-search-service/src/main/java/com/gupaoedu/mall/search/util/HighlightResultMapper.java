package com.gupaoedu.mall.search.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.bytes.ByteBufferReference;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.DefaultResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * 高亮
 *
 * @author Kang Yong
 * @date 2022/3/14
 * @since 1.0.0
 */
public class HighlightResultMapper extends DefaultResultMapper {

    /**
     * 映射转换，将非高亮数据转换成高亮数据
     *
     * @param response {@link SearchResponse}
     * @param clazz    {@link Class<T>}
     * @param pageable {@link Pageable}
     * @return {@link AggregatedPage<T>}
     * @author Kang Yong
     * @date 2022/3/14
     */
    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
        // 1、获取所有非高亮数据
        SearchHits hits = response.getHits();

        // 2、遍历非高亮数据集合
        for (SearchHit hit : hits) {
            // 非高亮数据
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            // 3、获取高亮数据
            for (Map.Entry<String, HighlightField> entry : hit.getHighlightFields().entrySet()) {
                // 4、将非高亮数据替换成高亮数据
                String key = entry.getKey();
                if (sourceAsMap.containsKey(key)) {
                    // 高亮碎片
                    String hlResult = this.transToTextToArrayToString(entry.getValue().getFragments());
                    if (StringUtils.isNotEmpty(hlResult)) {
                        // 替换高亮
                        sourceAsMap.put(key, hlResult);
                    }
                }
            }

            // 更新hit的数据
            hit.sourceRef(new ByteBufferReference(ByteBuffer.wrap(JSONObject.toJSONString(sourceAsMap).getBytes())));
        }

        return super.mapResults(response, clazz, pageable);
    }

    /**
     * Text转成字符串
     *
     * @param fragments
     * @return
     */
    public String transToTextToArrayToString(Text[] fragments) {
        if (fragments != null) {
            StringBuffer sbStr = new StringBuffer();
            for (Text fragment : fragments) {
                sbStr.append(fragment);
            }
            return sbStr.toString();
        }
        return null;
    }
}
