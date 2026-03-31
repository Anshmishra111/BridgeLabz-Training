package annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

// ---------- CUSTOM ANNOTATION ----------
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CacheResult {
}

// ---------- SERVICE WITH EXPENSIVE METHOD ----------
class ExpensiveService {

    @CacheResult
    public int computeSquare(int x) {
        System.out.println("Computing square for: " + x);
        return x * x;
    }
}

// ---------- CACHE HANDLER ----------
class CacheManager {

    private static final Map<String, Object> cache = new HashMap<>();

    public static Object invokeWithCache(Object obj,
                                         String methodName,
                                         Class<?>[] paramTypes,
                                         Object[] args) {

        try {
            Class<?> cls = obj.getClass();
            Method method = cls.getMethod(methodName, paramTypes);

            // Create unique cache key (method + parameters)
            String cacheKey = methodName + "-" + java.util.Arrays.toString(args);

            // Check annotation & cache
            if (method.isAnnotationPresent(CacheResult.class)) {

                if (cache.containsKey(cacheKey)) {
                    System.out.println("Returning cached result for: " + cacheKey);
                    return cache.get(cacheKey);
                }

                // Compute and cache result
                Object result = method.invoke(obj, args);
                cache.put(cacheKey, result);
                return result;
            }

            // If no annotation, just invoke
            return method.invoke(obj, args);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

// ---------- MAIN CLASS ----------
public class CacheResultDemo {

    public static void main(String[] args) {

        ExpensiveService service = new ExpensiveService();

        // First call (computed)
        System.out.println("Result: " +
                CacheManager.invokeWithCache(
                        service,
                        "computeSquare",
                        new Class[]{int.class},
                        new Object[]{5}
                )
        );

        // Second call with same input (cached)
        System.out.println("Result: " +
                CacheManager.invokeWithCache(
                        service,
                        "computeSquare",
                        new Class[]{int.class},
                        new Object[]{5}
                )
        );

        // Different input (computed again)
        System.out.println("Result: " +
                CacheManager.invokeWithCache(
                        service,
                        "computeSquare",
                        new Class[]{int.class},
                        new Object[]{6}
                )
        );
    }
}
