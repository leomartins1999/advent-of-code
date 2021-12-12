require_relative 'connections_builder'
require_relative 'cave_graph'

# Solution for day 10 of Advent of Code 2021
class Day12

  def initialize
    @connections_builder = ConnectionsBuilder.new
  end

  def solve_first(input)
    connections = @connections_builder.build(input)

    graph = CaveGraph.new(connections)

    paths = graph.get_paths

    paths.size
  end

  def solve_second(input)
    connections = @connections_builder.build(input)

    graph = CaveGraph.new(connections)

    paths = graph.get_paths(true)

    paths.size
  end
  
end
