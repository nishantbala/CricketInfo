package com.stackroute.favouriteservice.Model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ProviderDeserializer extends StdDeserializer<Provider> {
 
    public ProviderDeserializer() {
        this(null);
    }
 
    public ProviderDeserializer(Class<?> vc) {
        super(vc);
    }
 
    @Override
    public Provider deserialize(JsonParser jp, DeserializationContext ctxt) 
      throws IOException {
        JsonNode providerNode = jp.getCodec().readTree(jp);
        return new Provider(providerNode.get("url").textValue(),
        					providerNode.get("source").textValue(),
        					providerNode.get("pubDate").textValue());
    }
}