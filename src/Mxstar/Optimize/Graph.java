package Mxstar.Optimize;

import Mxstar.IR.Operand.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.BiConsumer;

public class Graph {
    public HashMap<VirtualReg, HashSet<VirtualReg>> graph;

    public Graph(Graph g) {
        graph = new HashMap<>();
        for (VirtualReg virReg: g.getAllRegs())
            graph.put(virReg, new HashSet<>(g.getNeighbor(virReg)));
    }

    public Graph() {
        graph = new HashMap<>();
    }
    public HashSet<VirtualReg> getNeighbor(VirtualReg virReg) {
        return graph.getOrDefault(virReg, new HashSet<>());
    }
    public Collection<VirtualReg> getAllRegs() {
        return graph.keySet();
    }

    public void addRegisters(Collection<VirtualReg> regs) {
        for (VirtualReg reg: regs){
            if (!graph.containsKey(reg))
                graph.put(reg, new HashSet<>());
        }
    }

    public void delReg(VirtualReg virReg) {
        for (VirtualReg reg: getNeighbor(virReg)) {
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

    public void clear() {
        graph.clear();
    }


    public int getDeg(VirtualReg virReg) {
        return graph.get(virReg).size();
    }


}
