package com.sennan.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Component
public class JsonUtil {

    public String convert(String json) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        // 使用StringJoiner来拼接所有的content，可以根据需要更改分隔符
        StringJoiner joiner = new StringJoiner("\n"); // 这里使用换行符作为分隔符，你也可以选择其他分隔符如","

        // 遍历"data"下的"block"数组，并进一步遍历其中的"line"和"word"
        JsonNode blocks = rootNode.path("data").path("block");
        for (JsonNode block : blocks) {
            for (JsonNode line : block.withArray("line")) {
                for (JsonNode word : line.withArray("word")) {
                    String content = word.path("content").asText();
                    if (!content.isEmpty()) {
                        joiner.add(content);
                    }
                }
            }
        }
        String result = joiner.toString();

        //去除字符串中的\n
        result = result.replaceAll("\n",",");

        return result;
    }

}


