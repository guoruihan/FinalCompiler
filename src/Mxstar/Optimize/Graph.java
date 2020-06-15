package Mxstar.Optimize;

import Mxstar.IR.Operand.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.BiConsumer;

public class Graph {
    public HashMap<VirtualReg, HashSet<VirtualReg>> graph;

    public Graph() {
        graph = new HashMap<>();
    }
    public Graph(Graph g) {
        graph = new HashMap<>();
        for (VirtualReg virReg: g.getAllRegs())
            graph.put(virReg, new HashSet<>(g.getAdjacent(virReg)));
    }

    public HashSet<VirtualReg> getAdjacent(VirtualReg virReg) {
        return graph.getOrDefault(virReg, new HashSet<>());
    }
    public Collection<VirtualReg> getAllRegs() {
        return graph.keySet();
    }

    public void addRegister(VirtualReg virReg) {
        if (!graph.containsKey(virReg))
            graph.put(virReg, new HashSet<>());
    }

    public void addRegisters(Collection<VirtualReg> regs) {
        for (VirtualReg reg: regs){
            addRegister(reg);
        }
    }

    public void delReg(VirtualReg virReg) {
        for (VirtualReg reg: getAdjacent(virReg)) {
            graph.get(reg).remove(virReg);
        }
        graph.remove(virReg);
    }

    public void addEdge(VirtualReg a, VirtualReg b) {
        if (a == b)
            return ;
        graph.get(a).add(b);
        graph.get(b).add(a);
    }

    public void removeEdge(VirtualReg a, VirtualReg b) {
        if (graph.containsKey(a) && graph.get(a).contains(b)) {
            graph.get(a).remove(b);
            graph.get(b).remove(a);
        }
    }

    public void clear() {
        graph.clear();
    }

    public void forEach(BiConsumer<VirtualReg, VirtualReg> consumer ) {
        for (VirtualReg reg1: graph.keySet())
            for (VirtualReg reg2: graph.get(reg1)) {
                consumer.accept(reg1, reg2);
            }
    }

    public int getDeg(VirtualReg virReg) {
        return graph.get(virReg).size();
    }


}
