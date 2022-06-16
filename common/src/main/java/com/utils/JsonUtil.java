package com.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.common.base.Preconditions;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/2/9 6:05 下午
 * description：
 */
@EqualsAndHashCode
@ToString
public class JsonUtil {

    public static final JsonUtil INSTANCE = new JsonUtil();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String CLASS_NULL = "class is null";

    public JsonUtil() {
        this.objectMapper.setSerializationInclusion(Include.NON_NULL);
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.configure(Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        this.objectMapper.registerModule(new ParameterNamesModule()).registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
    }

    public static <T> T toObject(JsonNode jsonNode, Class<T> clazz) {
        return INSTANCE.asObject(jsonNode, clazz);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        return INSTANCE.asObject(json, clazz);
    }

    public static <T, W> W toObject(String json, Class<W> wrapperClass, Class<T> typeClass) {
        return INSTANCE.asObject(json, wrapperClass, typeClass);
    }

    public static <T> T toObject(String json, TypeReference<T> typeReference) {
        return INSTANCE.asObject(json, typeReference);
    }

    public static <T> T toObject(byte[] json, Class<T> clazz) {
        return INSTANCE.asObject(json, clazz);
    }

    public static <T> List<T> toList(String json, Class<T> clazz) {
        return INSTANCE.asList(json, clazz);
    }

    public static <T> Set<T> toSet(String json, Class<T> clazz) {
        return INSTANCE.asSet(json, clazz);
    }

    public static <K, V> Map<K, V> toMap(String json, Class<K> keyClass, Class<V> valueClass) {
        return INSTANCE.asMap(json, keyClass, valueClass);
    }

    public static String toString(Object object) {
        return INSTANCE.asString(object);
    }

    public static String toString(Object object, boolean pretty) {
        return INSTANCE.asString(object, pretty);
    }

    public static String prettyToString(Object object) {
        return INSTANCE.prettyAsString(object);
    }

    public static JsonNode readTree(String json) {
        return INSTANCE.asTree(json);
    }

    public static <T> T clone(T object, Class<T> clazz) {
        return INSTANCE.asClone(object, clazz);
    }

    public static ObjectNode getNode(Object object) {
        return INSTANCE.asNode(object);
    }

    public <T> T asObject(JsonNode jsonNode, Class<T> clazz) {
        if (jsonNode == null) {
            return null;
        } else {
            try {
                Preconditions.checkNotNull(clazz, CLASS_NULL);
                return this.objectMapper.treeToValue(jsonNode, clazz);
            } catch (Exception var4) {
                throw new JsonUtil.JsonException(var4);
            }
        }
    }

    public <T> T asObject(String json, Class<T> clazz) {
        try {
            if (StringUtils.isBlank(json)) {
                return null;
            } else {
                Preconditions.checkNotNull(clazz, CLASS_NULL);
                return clazz == String.class ? (T) json : this.objectMapper.readValue(json, clazz);
            }
        } catch (Exception var4) {
            throw new JsonUtil.JsonException(var4);
        }
    }

    public <T, W> W asObject(String json, Class<W> wrapperClass, Class<T> typeClass) {
        if (StringUtils.isBlank(json)) {
            return null;
        } else {
            try {
                Preconditions.checkNotNull(wrapperClass, "wrapperClass is null");
                Preconditions.checkNotNull(typeClass, "typeClass is null");
                JavaType type = this.objectMapper.getTypeFactory().constructParametricType(wrapperClass, new Class[]{typeClass});
                return this.objectMapper.readValue(json, type);
            } catch (Exception var5) {
                throw new JsonUtil.JsonException(var5);
            }
        }
    }

    public <T> T asObject(String json, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(json)) {
            return null;
        } else {
            try {
                Preconditions.checkNotNull(typeReference, "typeReference is null");
                return this.objectMapper.readValue(json, typeReference);
            } catch (Exception var4) {
                throw new JsonUtil.JsonException(var4);
            }
        }
    }

    public <T> T asObject(byte[] bytes, Class<T> clazz) {
        return bytes != null && bytes.length > 0 ? this.asObject(new String(bytes, StandardCharsets.UTF_8), clazz) : null;
    }

    public <T> List<T> asList(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return new ArrayList(0);
        } else {
            try {
                Preconditions.checkNotNull(clazz, CLASS_NULL);
                JavaType type = this.objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
                return (List) this.objectMapper.readValue(json, type);
            } catch (Exception var4) {
                throw new JsonUtil.JsonException(var4);
            }
        }
    }

    public <T> Set<T> asSet(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return new HashSet(0);
        } else {
            try {
                Preconditions.checkNotNull(clazz, CLASS_NULL);
                JavaType type = this.objectMapper.getTypeFactory().constructCollectionType(Set.class, clazz);
                return (Set) this.objectMapper.readValue(json, type);
            } catch (Exception var4) {
                throw new JsonUtil.JsonException(var4);
            }
        }
    }

    public <K, V> Map<K, V> asMap(String json, Class<K> keyClass, Class<V> valueClass) {
        if (StringUtils.isBlank(json)) {
            return null;
        } else {
            try {
                Preconditions.checkNotNull(keyClass, "key class is null");
                Preconditions.checkNotNull(valueClass, "value class is null");
                JavaType type = this.objectMapper.getTypeFactory().constructParametricType(Map.class, new Class[]{keyClass, valueClass});
                return (Map) this.objectMapper.readValue(json, type);
            } catch (Exception var5) {
                throw new JsonUtil.JsonException(var5);
            }
        }
    }

    public String asString(Object object) {
        if (object == null) {
            return "";
        } else if (object instanceof String) {
            return (String) object;
        } else {
            try {
                return this.objectMapper.writeValueAsString(object);
            } catch (Exception var3) {
                throw new JsonUtil.JsonException(var3);
            }
        }
    }

    public String asString(Object object, boolean pretty) {
        return pretty ? this.prettyAsString(object) : this.asString(object);
    }

    public String prettyAsString(Object object) {
        if (object == null) {
            return "";
        } else {
            try {
                if (object instanceof String) {
                    String string = (String) object;
                    if (string.startsWith("{") && string.endsWith("}")) {
                        JsonNode jsonNode = readTree(string);
                        return this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
                    } else {
                        return string;
                    }
                } else {
                    return this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
                }
            } catch (Exception var4) {
                throw new JsonUtil.JsonException(var4);
            }
        }
    }

    public JsonNode asTree(String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        } else {
            try {
                return this.objectMapper.readTree(json);
            } catch (Exception var3) {
                throw new JsonUtil.JsonException(var3);
            }
        }
    }

    public <T> T asClone(T object, Class<T> clazz) {
        if (object == null) {
            return null;
        } else {
            try {
                Preconditions.checkNotNull(clazz, CLASS_NULL);
                String json = toString(object);
                return toObject(json, clazz);
            } catch (Exception var4) {
                throw new JsonUtil.JsonException(var4);
            }
        }
    }

    public ObjectNode asNode(Object object) {
        return object == null ? null : (ObjectNode) this.objectMapper.convertValue(object, ObjectNode.class);
    }

    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }


    public static class JsonException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        private JsonException(Throwable cause) {
            super(cause);
        }
    }


}
