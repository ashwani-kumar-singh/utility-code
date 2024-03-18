package datastructure;

/*
 Write an implementation to a method that return Roles that can access for a given directory path.

 Get the set of roles from role map that can access given directory location. If not present then
 look into it's closest parent directories for which roles are present.

        ********************************************************************************
        | Directory Path                            |         Roles                    |
        |******************************************************************************|
        |  C:/Project/Confidential Info             |                {Project Manager} |
        |  C:/Project/Software/Release/Versions     |        {Developer, Lead, Tester} |
        |  C:                                       |    {Project Manager, Developer,  |
        |                                           |                Lead, Tester}     |
        |  C:/Project/Software/Developer Tool       |    {Developer, Lead}             |
        ********************************************************************************

Example:
1.	Roles that can access Directory Path "C:" are Project Manager, Developer, Lead and Tester.
2.	Roles that can access Directory Path "C:/XYZ" are Project Manager, Developer, Lead and Tester
    (Since role is not pre-defined for path "C:/XYZ" it will inherit roles from its parent directory i.e. "C:").

3.	Roles that can access Directory Path "C:/Project/Software/Release/Versions" are Developer, Lead and Tester.
4.	Roles that can access Directory Path "C:/Project/Confidential Info" is Project Manager.
5.	Roles that can access Directory Path "C:/Project/Software/Developer Tool" are Developer and Lead.
6.	Roles that can access path C:/Project/Confidential Info/Release Notes/Documents is Project Manager
    (Since role is not pre-defined for path "C:/Project/Confidential Info/Release Notes/Documents" it will
    inherit roles from its parent directory for which Role/Roles is present i.e. "C:/Project/Confidential Info").

 */


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DirectoryAccess {

    private static Set<String> getRoles(String directoryPath) {

        Map<String, Set<String>> roleMap = getDirectoryRoleMap();
        Set<String> roles = new HashSet<>();
        StringBuilder path = new StringBuilder(directoryPath);
        while (path.length() > 0) {
            roles = roleMap.get(path.toString());
            if (roles != null && roles.size() != 0) {
                return roles;
            }
            int lastIndex = path.lastIndexOf("/");
            path = lastIndex != -1 ?
                    new StringBuilder(path.substring(0, lastIndex)) :
                    new StringBuilder("");
        }
        return roles;
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String directoryPath= sc.nextLine();

        /*case 1: String directoryPath = null or empty; output: Directory Path can not be null or empty!
        case 2: String directoryPath = "" ; output: Exception in thread "main" java.lang.RuntimeException: Directory
         Path can not be null or empty!
        case 3: String directoryPath = " " ; output: Roles that can access directory {   } are: null
        case 4: String directoryPath = "C:/developer_tools" ; output: Roles that can access directory
         { C:/developer_tools } are: [admin, developer]
        case 5: String directoryPath = "C:/confidential_info/" ; output: Roles that can access directory
         { C:/confidential_info/ } are: [manager, director]
        case 5: String directoryPath = "C:/xyz/" ; output: Roles that can access directory { C:/xyz/ }
         are: [super_admin]
         case 6: String directoryPath = "D:/xyz" ; output: No Role is defined that can access directory { D:/xyz }

         Time Complexity: O(n) ; where n is the number of "/"
         Auxiliary Space: O(1)
         */
        if (directoryPath == null || "".equals(directoryPath)) {
            System.out.println("Directory Path can not be null or empty!");
            return;
        }
        Set<String> roles = getRoles(directoryPath);
        if (roles == null || roles.isEmpty()) {
            System.out.println("No Role is defined that can access directory path { " + directoryPath + " }");
        } else {
            System.out.println("Roles that can access directory path { " + directoryPath +
                    " } are: " + roles);
        }
    }

    private static Map<String, Set<String>> getDirectoryRoleMap() {
        Map<String, Set<String>> roleMap = new HashMap<>();
        Set<String> roles = new HashSet<>();
        roles.add("Project Manager");
        roles.add("Developer");
        roles.add("Lead");
        roles.add("Tester");
        roleMap.put("C:", roles);
        roles = new HashSet<>();
        roles.add("Developer");
        roles.add("Lead");
        roles.add("Tester");
        roleMap.put("C:/Project/Software/Release/Versions", roles);
        roles = new HashSet<>();
        roles.add("Project Manager");
        roleMap.put("C:/Project/Confidential Info", roles);
        roles = new HashSet<>();
        roles.add("Developer");
        roles.add("Lead");
        roleMap.put("C:/Project/Software/Developer Tool", roles);
        return roleMap;
    }
}
