package study.basic.chatapp.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class WebSocketChannelInterceptor implements ChannelInterceptor {
    private final ConcurrentHashMap<String, AtomicInteger> topicSubscriberCount = new ConcurrentHashMap<>();

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        if (SimpMessageType.SUBSCRIBE.name().equals(accessor.getCommand().name())) {
            String destination = accessor.getDestination();
            topicSubscriberCount.computeIfAbsent(destination, k -> new AtomicInteger(0)).incrementAndGet();
        } else if (SimpMessageType.UNSUBSCRIBE.name().equals(accessor.getCommand().name())) {
            String destination = accessor.getDestination();
            topicSubscriberCount.computeIfAbsent(destination, k -> new AtomicInteger(0)).decrementAndGet();
        }
        return message;
    }
    public int getSubscriberCount(String destination) {
        return topicSubscriberCount.getOrDefault(destination, new AtomicInteger(0)).get();
    }
}