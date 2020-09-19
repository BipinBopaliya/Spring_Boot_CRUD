package com.example.demo.controller;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;

public class Producer {

    private Map<String, Object> props = new HashMap<>();

    Producer(){
        this.props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        this.props.put(ProducerConfig.RETRIES_CONFIG, 0);
        this.props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        this.props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        this.props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        this.props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        this.props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    }

    public void produce() {

        List<KeyValue<String, String>> userClicks = Arrays.asList(
                new KeyValue<>("alice", "ABC"),
                new KeyValue<>("bob", "DEF"),
                new KeyValue<>("chao", "GHI"),
                new KeyValue<>("bob", "JKL"),
                new KeyValue<>("dave", "MNO")
        );

        DefaultKafkaProducerFactory<String, String> pf = new DefaultKafkaProducerFactory<>(this.props);
        KafkaTemplate<String, String> template = new KafkaTemplate<>(pf, true);
        template.setDefaultTopic("user-clicks");

        for (KeyValue<String,String> keyValue : userClicks) {
            template.sendDefault(keyValue.key, keyValue.value);
        }
    }

//        public IntFunction<KStream<String, String>> produce2(){
////            System.out.print("Hello2");
////            StreamsBuilder sb = new StreamsBuilder();
////            KStream<String, String> ks= sb.stream("user-clicks");
//
//            Properties props = new Properties();
//            props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
//            props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-broker1:9092");
//            props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//            props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//
//            StreamsBuilder builder = new StreamsBuilder();
//            KStream<String, String> textLines = builder.stream("TextLinesTopic");
//            KTable<String, Long> wordCounts = textLines
//                    .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
//                    .groupBy((key, word) -> word)
//                    .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));
//            wordCounts.toStream().to("WordsWithCountsTopic", Produced.with(Serdes.String(), Serdes.Long()));
//
//            return (IntFunction<KStream<String, String>>) textLines;
//
//        }
    public void Foo(){
    }
@Bean
public KStream<String, String> produce2() {
//public KStream<String, String> produce2() {
    StreamsBuilder sb = new StreamsBuilder();
    KStream<String, String> stream = sb.stream("user-clicks");
//    stream.mapValues((ValueMapper<String, String>) String::toUpperCase)
//            .mapValues(Foo::new)
//            .through(FOOS, Produced.with(Serdes.Integer(), new JsonSerde<Foo>() {
//            }))
//            .mapValues(Foo::getName)
//            .groupByKey()
//            .windowedBy(TimeWindows.of(1000))
//            .reduce((value1, value2) -> value1 + value2, Materialized.as("windowStore"))
//            .toStream()
//            .map((windowedId, value) -> new KeyValue<>(windowedId.key(), value))
//            .filter((i, s) -> s.length() > 40).to(streamingTopic2);
//    stream.print(Printed.toSysOut());
    stream.map(
            (key, value) -> new KeyValue<String, String>("ABC", "DEF")
    );
    System.out.println("HI...HELLO");
    return stream;
}
}
