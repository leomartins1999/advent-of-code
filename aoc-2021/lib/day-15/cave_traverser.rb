STARTING_POINT = [0, 0].freeze

class CaveTraverser
  def initialize(map)
    @map = map

    @max_x = @map.first.size - 1
    @max_y = @map.size - 1

    @end_point = [@max_x, @max_y]

    @graph = get_graph
  end

  def get_best_risk(start = STARTING_POINT, _end = @end_point)
    get_risk(start, _end)
  end

  private

  # dijkstra algorithm
  def get_risk(start, _end)
    graph = @graph.dup
    
    graph[start][:risk] = 0 # setting risk for start node

    visiting = [start] # initializing algorithm by beggining at the start point

    while !visiting.empty?
      point = visiting.pop
      from = graph[point]

      next if point == _end || from[:visited] # break if the node does not need a visit/is the destination node

      from[:others].each do |other|
        to = graph[other]
        
        curr_risk = to[:risk]
        new_risk = from[:risk] + to[:value]

        # if we found a path/cheaper path
        if curr_risk.nil? || new_risk <= curr_risk
          to[:risk] = new_risk # set cheaper cost for node
          to[:visited] = false # re-enqueue node for visit
          
          visiting = visiting.unshift(other) 
        end
      end

      from[:visited] = true # mark node as visited
    end

    graph[_end][:risk]
  end

  def get_graph
    (0..@max_x).reduce({}) do |acc, x|
      line_nodes = (0..@max_y).reduce({}) do |acc, y|
        acc.merge({[x, y] => get_node(x, y)})
      end

      acc.merge(line_nodes)
    end
  end

  def get_node(x, y)
    {
      :pos => [x, y],
      :value => get_value(x, y),
      :others => get_adjacent(x, y),
      :visited => false, 
      :risk => nil
    }
  end

  def get_value(x, y)
    @map[y][x]
  end 

  def get_adjacent(x, y)
    up = (y > 0) ? [x, y - 1] : nil
    right = (x < @max_x) ? [x + 1, y] : nil
    down = (y < @max_y) ? [x, y + 1] : nil
    left = (x > 0) ? [x - 1, y] : nil

    [up, right, down, left].reject(&:nil?)
  end

end
