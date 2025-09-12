package config


class TempGlobalVariables {

	private static Map<String, Object> tempVars = [:]

	// Set temporary global variable
	static void set(String key, Object value) {
		tempVars[key] = value
	}

	// Get temporary global variable
	static Object get(String key) {
		return tempVars.containsKey(key) ? tempVars[key] : null
	}

	// Clear one temporary global variable
	static void clear(String key) {
		tempVars.remove(key)
	}

	// Clear all temporary global variables
	static void clearAll() {
		tempVars.clear()
	}
}
