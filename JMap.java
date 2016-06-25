public class JMap<K, V> {

    private GenericList<K> keySet;
    private GenericList<V> valueSet;

    public JMap() {
        keySet = new GenericList<K>();
        valueSet = new GenericList<V>();

    }

    public void put(K key, V value) {
        if (!keySet.contains(key)) {
            // if the key is not in the map, create a new entry
            keySet.add(key);
            valueSet.add(value);
        }

        if (keySet.contains(key)) {
            // if the key is already in the map, update the current value.
            valueSet.updateValueAtIndex(keySet.getIndexOf(key), value);
        }
    }

    @Override
    public String toString() {
        return keySet.toString() + valueSet.toString();
    }

    public V get(K key) {
        int index = keySet.getIndexOf(key);
        return valueSet.getElementAtIndex(index);
    }

    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    public void clear() {
        keySet.clear();
        valueSet.clear();
    }

    public int size() {
        return keySet.size();
    }

    public void remove(K key) {
        int indexOfRemoval = keySet.findIndexOf(key);
        keySet.removeValue(indexOfRemoval);
        valueSet.removeValue(indexOfRemoval);
    }
}
