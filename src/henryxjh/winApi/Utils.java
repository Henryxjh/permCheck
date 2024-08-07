package henryxjh.winApi;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.charset.StandardCharsets;

public class Utils {
    /**
     * Parse the {@code String} to C type
     * @param arena the arena can place a memory segment
     * @param str the string to parse
     * @return the memory segment contains the C string
     */
    public static MemorySegment toCString(Arena arena, String str) {
        var bytes = str.getBytes(StandardCharsets.UTF_16LE);
        var segment = arena.allocate(bytes.length + 2);
        segment.copyFrom(MemorySegment.ofArray(bytes));
        segment.set(ValueLayout.JAVA_BYTE, bytes.length, (byte) 0);
        segment.set(ValueLayout.JAVA_BYTE, bytes.length + 1, (byte) 0);
        return segment;
    }
}
