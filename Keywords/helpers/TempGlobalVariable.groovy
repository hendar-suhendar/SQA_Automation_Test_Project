package helpers

class TempGlobalVariable {
	private static Map<String,Object> tempVars = [:]

	static void set(String key, Object value) {
		tempVars[key] = value
	}

	static Object get(String key) {
		return tempVars.containsKey(key) ? tempVars[key] : null
	}

	static void clear(String key) {
		tempVars.remove(key)
	}

	static void clearAll() {
		tempVars.clear()
	}

	//Tambahan supaya bisa ambil semua key-value
	static Map<String,Object> getAll() {
		return tempVars
	}
}