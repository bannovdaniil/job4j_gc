# job4j_gc

### 2.4. Garbage Collection

Сборка мусора, как оно тривиально не звучало бы, выполняется сборщиком мусора (Garbage Collector (GC)).
GC – часть JVM, прикладная программа, которая занимается очищением памяти.

#### 2.4.3. Профилирование приложения

4. Найти утечку памяти [ru.job4j.gc.leak].
   Нужно поправить код, устранив факторы, которые могут привести к утечке памяти, а также оптимизировать код в местах,
   где создаются лишние объекты в хипе. Все подсказки по ссылкам в источниках.
