package gov.nist.csd.pm.pip.graph.model.nodes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Stores information needed for a node.
 */
public class Node implements Serializable {
    private long                id;
    private String              name;
    private NodeType            type;
    private Map<String, String> properties;

    public Node() {
        this.properties = new HashMap<>();
    }

    public Node(String name, NodeType type, Map<String, String> properties) {
        this.name = name;
        this.type = type;
        this.properties = properties == null ? new HashMap<>() : properties;
    }

    public Node(long id, String name, NodeType type, Map<String, String> properties) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.properties = properties == null ? new HashMap<>() : properties;
    }

    public Node(long id, NodeType type) {
        this.id = id;
        this.type = type;
    }

    public Node id(long id) {
        if (id == 0) {
            throw new IllegalArgumentException("a node cannot have an ID of 0");
        }

        this.id = id;
        return this;
    }

    public Node name(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("a node can not have a null or empty name");
        }

        this.name = name;
        return this;
    }

    public Node type(NodeType type) {
        if (type == null) {
            throw new IllegalArgumentException("a type cannot be null");
        }
        this.type = type;
        return this;
    }

    public Node properties(Map<String, String> properties) {
        if (properties == null) {
            properties = new HashMap<>();
        }

        this.properties = properties;
        return this;
    }

    public Node property(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("a node cannot have a property with a null key or value");
        }

        this.properties.put(key, value);
        return this;
    }

    public long getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public NodeType getType() {
        return type;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    /**
     * Two nodes are equal if their IDs are the same.
     *
     * @param o The object to check for equality.
     * @return true if the two objects are the same, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Node) {
            Node n = (Node) o;
            return this.name.equals(n.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name + ":" + type + ":" + id + ":" + properties;
    }

    /**
     * This method receives an array of strings and pairs consecutive parameters as key, value pairs.
     * For example, calling toProperties('prop1', 'value1', 'prop2', 'value2') would create a property map with two
     * entries.  The first entry will be 'prop1' to 'value1' and the second will be 'prop2' to 'value2'. An
     * IllegalArgumentException will be thrown if any value is null or there is an odd number of values, as this will
     * lead to errors in processing the parameters.
     *
     * @param pairs Array of string values to convert to a HashMap
     * @return a HashMap of the given pairs
     */
    public static Map<String, String> toProperties(String... pairs) {
        HashMap<String, String> props = new HashMap<>();
        for (int i = 0; i < pairs.length - 1; i++) {
            props.put(pairs[i], pairs[++i]);
        }
        return props;
    }
}
