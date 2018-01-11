# search.py
# ---------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
#
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


"""
In search.py, you will implement generic search algorithms which are called by
Pacman agents (in searchAgents.py).
"""

import util

class SearchProblem:
    """
    This class outlines the structure of a search problem, but doesn't implement
    any of the methods (in object-oriented terminology: an abstract class).

    You do not need to change anything in this class, ever.
    """

    def getStartState(self):
        """
        Returns the start state for the search problem.
        """
        util.raiseNotDefined()

    def isGoalState(self, state):
        """
          state: Search state

        Returns True if and only if the state is a valid goal state.
        """
        util.raiseNotDefined()

    def getSuccessors(self, state):
        """
          state: Search state

        For a given state, this should return a list of triples, (successor,
        action, stepCost), where 'successor' is a successor to the current
        state, 'action' is the action required to get there, and 'stepCost' is
        the incremental cost of expanding to that successor.
        """
        util.raiseNotDefined()

    def getCostOfActions(self, actions):
        """
         actions: A list of actions to take

        This method returns the total cost of a particular sequence of actions.
        The sequence must be composed of legal moves.
        """
        util.raiseNotDefined()


def tinyMazeSearch(problem):
    """
    Returns a sequence of moves that solves tinyMaze.  For any other maze, the
    sequence of moves will be incorrect, so only use this for tinyMaze.
    """
    from game import Directions
    s = Directions.SOUTH
    w = Directions.WEST
    return  [s, s, w, s, w, w, s, w]

def depthFirstSearch(problem):
    """
    Search the deepest nodes in the search tree first.

    Your search algorithm needs to return a list of actions that reaches the
    goal. Make sure to implement a graph search algorithm.

    To get started, you might want to try some of these simple commands to
    understand the search problem that is being passed in:

    print "Start:", problem.getStartState()
    print "Is the start a goal?", problem.isGoalState(problem.getStartState())
    print "Start's successors:", problem.getSuccessors(problem.getStartState())
    """
    "*** YOUR CODE HERE ***"
    #util.raiseNotDefined()

    state = problem.getStartState()
    frontier = util.Stack()
    explored = list()

    """
    each node is a map of 3 keys
    parent: the parent node of the current node
    state: the state(position) of the current node
    action: the action made to reach the current node
    """
    node ={}
    node["parent"]=None
    node["state"] = state
    node["action"] = None

    frontier.push(node)
    """
    while still there are more nodes to explore
    """
    while not frontier.isEmpty():
        node = frontier.pop()
        state = node["state"]
        """
        if already explored continue
        """
        if state in explored:
            continue
        explored.append(state)
        """
        stop when goal is found
        """
        if problem.isGoalState(state) == True:
            break
        for neighbour in  problem.getSuccessors(state):
            if not neighbour[0] in explored :
                temp_node={}
                temp_node["parent"] = node
                temp_node["state"]=neighbour[0]
                temp_node["action"]=neighbour[1]
                frontier.push(temp_node)

    #actions
    actions=[]
    while node["action"] != None:
        actions.insert(0, node["action"])
        node = node["parent"]

    return actions


def breadthFirstSearch(problem):
    """Search the shallowest nodes in the search tree first."""
    "*** YOUR CODE HERE ***"
    state = problem.getStartState()
    frontier = util.Queue()
    explored = list()

    """
    each node is a map of 3 keys
    parent: the parent node of the current node
    state: the state of the current node
    action: the action made to reach the current node
    """
    node ={}
    node["parent"]=None
    node["state"] = state
    node["action"] = None

    frontier.push(node)
    """
    while still there are more nodes to explore
    """
    while not frontier.isEmpty():
        node = frontier.pop()
        state = node["state"]
        """
        if already explored continue
        """
        if state in explored:
            continue
        explored.append(state)
        """
        stop when goal is found
        """
        if problem.isGoalState(state) == True:
            break
        for neighbour in  problem.getSuccessors(state):
            if not neighbour[0] in explored :
                temp_node={}
                temp_node["parent"] = node
                temp_node["state"]=neighbour[0]
                temp_node["action"]=neighbour[1]
                frontier.push(temp_node)

    #actions
    actions=[]
    while node["action"] != None:
        actions.insert(0, node["action"])
        node = node["parent"]

    return actions

def uniformCostSearch(problem):
    """Search the node of least total cost first."""
    
    frontier = util.PriorityQueue();
    explored = set();
    """Push the start state into the frontier with cost = 0 and priority = 0  """
    frontier.push((problem.getStartState(),[],0),0);

    while not frontier.isEmpty():
        currState, currDirection, currCost = frontier.pop();

        if(currState in explored):
            continue;

        """Return the direction if the current state is the goal state """
        if problem.isGoalState(currState):
            return currDirection;

        explored.add(currState);

        """Iterate over the successor of the current state to add non explored one to frontier """
        for succState, succDirection, succCost in problem.getSuccessors(currState):
            if(succState in explored):
                continue;
            """Add the successorNode to te frontier queue with the new priority """
            frontier.push((succState, currDirection+[succDirection], currCost+succCost),currCost+succCost);


def nullHeuristic(state, problem=None):
    """
    A heuristic function estimates the cost from the current state to the nearest
    goal in the provided SearchProblem.  This heuristic is trivial.
    """
    return 0

def aStarSearch(problem, heuristic=nullHeuristic):
    """Search the node that has the lowest combined cost and heuristic first."""
    
    frontier = util.PriorityQueue();
    explored = set();
    """Push the start state into the frontie with cost = 0 and priority = 0  """
    frontier.push((problem.getStartState(),[],0),0);

    while not frontier.isEmpty():
        currState, currDirection, currCost = frontier.pop();

        if(currState in explored):
            continue;

        """Return the direction if the current state is the goal state """
        if problem.isGoalState(currState):
            return currDirection;

        explored.add(currState);

        """Iterate over the successor of the current state to add non explored one to frontier """
        for succState, succDirection, succCost in problem.getSuccessors(currState):
            if(succState in explored):
                continue;
            """Calcultate the heuritsic of the successor state to add it as a priority """
            heuristicVal = heuristic(succState, problem);
            """Add the successorNode to te frontier queue with the new priority """
            frontier.push((succState, currDirection+[succDirection], currCost+succCost),currCost+succCost+heuristicVal);
    


# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
astar = aStarSearch
ucs = uniformCostSearch