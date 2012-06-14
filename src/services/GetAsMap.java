package services;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class GetAsMap {
	public static List<? extends Map<String, ?>> getAsMap(List<?> objects, String[] fieldsToGet) {
		ArrayList<Map<String, ?>> list = new ArrayList<Map<String, ?>>();
		for (Object object : objects) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Class<? extends Object> c = object.getClass();
			for (Field field : c.getFields()) {
				Annotation[] ann = field.getAnnotations();
				for (@SuppressWarnings("unused") Annotation annotation : ann) {
						try {
							boolean found = false;
							for (String looking : fieldsToGet) {
								if (looking.equals(field.getName())) {
									found = true;
									break;
								}
							}
							if (found) {
								map.put(field.getName(), field.get(object));
							}

						} catch (IllegalArgumentException eee) {
								return list;
						} catch (IllegalAccessException ee) {
							return list;
						}
					
				}
			}
			list.add(map);
			
		}
		return list;
	}
}
