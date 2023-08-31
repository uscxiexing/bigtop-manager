package org.apache.bigtop.manager.server.stack.dag;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DagHelper {

    private static final String ROLE_COMMAND_SPLIT = "-";

    @Getter
    private static final Map<String, DAG<ComponentCommandWrapper, ComponentCommandWrapper, DagGraphEdge>> stackDagMap = new HashMap<>();


    /**
     * Initialize the DAG for each stack
     * @param stackDependencyMap {@code Map<BIGTOP-3.2.0=Map<blockedRole, Set<blockerRole>>>}
     */
    public static void dagInitialized(Map<String, Map<String, Set<String>>> stackDependencyMap) {

        for (Map.Entry<String, Map<String, Set<String>>> mapEntry : stackDependencyMap.entrySet()) {

            String fullStackName = mapEntry.getKey();
            DAG<ComponentCommandWrapper, ComponentCommandWrapper, DagGraphEdge> dag = new DAG<>();

            for (Map.Entry<String, Set<String>> entry : mapEntry.getValue().entrySet()) {
                String key = entry.getKey();
                Set<String> blockers = entry.getValue();

                String[] blockedTuple = key.split(ROLE_COMMAND_SPLIT);
                String blockedRole = blockedTuple[0];
                String blockedCommand = blockedTuple[1];

                for (String blocker : blockers) {
                    String[] blockerTuple = blocker.split(ROLE_COMMAND_SPLIT);
                    String blockerRole = blockerTuple[0];
                    String blockerCommand = blockerTuple[1];


                    ComponentCommandWrapper blockedRcp = new ComponentCommandWrapper(blockedRole, blockedCommand);
                    ComponentCommandWrapper blockerRcp = new ComponentCommandWrapper(blockerRole, blockerCommand);

                    DagGraphEdge roleCommandEdge = new DagGraphEdge(blockerRcp, blockedRcp);
                    dag.addEdge(blockerRcp, blockedRcp, roleCommandEdge, true);
                }

                stackDagMap.put(fullStackName, dag);
            }
        }


    }

}