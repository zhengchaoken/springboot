package sample.cluster.stats;

import java.util.HashMap;
import java.util.Map;

import akka.actor.AbstractActor;

/*
计算每个单词的长度
 */
public class StatsWorker extends AbstractActor {

  Map<String, Integer> cache = new HashMap<String, Integer>();

  @Override
  public Receive createReceive() {
    return receiveBuilder()
      .match(String.class, word -> {
        Integer length = cache.get(word);
        if (length == null) {
          length = word.length();
          cache.put(word, length);
        }
        sender().tell(length, self());
      })
      .build();
  }
}
