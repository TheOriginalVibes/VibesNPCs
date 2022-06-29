package at.vibes.domischmutz.npcs.npc;

import at.vibes.domischmutz.npcs.npc.function.NpcFunctionContext;
import at.vibes.domischmutz.npcs.npc.function.NpcFunctionModel;
import at.vibes.domischmutz.npcs.utility.PluginLocation;

import java.io.Serializable;
import java.util.*;

public class NpcModel implements Cloneable, Serializable {
    private int id;

    private double hologramHeight;
    private String skin, signature = "";
    private String pathName;
    private String glowName;
    private PluginLocation location;
    private String npcType;
    private List<String> hologramLines;
    private Map<String, NpcFunctionModel> npcFunctions;
    private Map<String, String[]> customizationMap;

    public NpcModel(int id) {
        this();
        this.id = id;
        this.npcType = "player";
        skin = "";
        signature = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NpcModel withId(int id) {
        setId(id);
        return this;
    }

    public double getHologramHeight() {
        return hologramHeight;
    }

    public void setHologramHeight(double hologramHeight) {
        this.hologramHeight = hologramHeight;
    }

    public NpcModel withHologramHeight(double hologramHeight) {
        setHologramHeight(hologramHeight);
        return this;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public NpcModel withSkin(String skin) {
        setSkin(skin);
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public NpcModel withSignature(String signature) {
        setSignature(signature);
        return this;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public NpcModel withPathName(String pathName) {
        setPathName(pathName);
        return this;
    }

    public String getGlowName() {
        return glowName;
    }

    public void setGlowName(String glowName) {
        this.glowName = glowName;
    }

    public NpcModel withGlowName(String glowName) {
        setGlowName(pathName);
        return this;
    }

    public List<String> getHologramLines() {
        return hologramLines;
    }

    public void setHologramLines(List<String> hologramLines) {
        this.hologramLines = hologramLines;
    }

    public NpcModel withHologramLines(List<String> hologramLines) {
        setHologramLines(hologramLines);
        return this;
    }

    public PluginLocation getLocation() {
        return location;
    }

    public void setLocation(PluginLocation location) {
        this.location = location;
    }

    public NpcModel withLocation(PluginLocation location) {
        setLocation(location);
        return this;
    }

    public String getNpcType() {
        return npcType;
    }

    public void setNpcType(String npcType) {
        this.npcType = npcType;
    }

    public NpcModel withNpcType(String npcType) {
        setNpcType(npcType);
        return this;
    }

    public Map<String, String[]> getCustomizationMap() {
        return customizationMap;
    }

    public void setCustomizationMap(Map<String, String[]> customizationMap) {
        this.customizationMap = customizationMap;
    }

    public NpcModel withCustomizationMap(Map<String, String[]> customizationMap) {
        setCustomizationMap(customizationMap);
        return this;
    }

    public Map<String, NpcFunctionModel> getFunctions() {
        return npcFunctions;
    }

    public void setFunctions(Map<String, NpcFunctionModel> npcFunctions) {
        this.npcFunctions = npcFunctions;
    }

    public NpcModel withFunctionValues(Map<String, NpcFunctionModel> npcFunctions) {
        setFunctions(npcFunctions);
        return this;
    }

    private NpcModel() {
        hologramLines = Collections.singletonList("/vnpcs lines");
        customizationMap = new HashMap<>();
        npcFunctions = new HashMap<>();
        npcFunctions.put("holo", new NpcFunctionModel(NpcFunctionContext.NULL_CONTEXT, true));
    }
}
