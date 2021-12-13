class PointsFolder
  def fold(points, line)
    points
      .map { |p| fold_point(p, line) }
      .uniq
      .reject { |x, y| x < 0 || y < 0 } # reject points that are out of the fold
  end

  private

  def fold_point(point, line)
    type, coord = line

    if type == 'x'
      fold_x(point, coord)
    elsif type == 'y'
      fold_y(point, coord)
    end
  end

  def fold_x(point, coord)
    x, y = point

    if x < coord
      point
    else
      [2 * coord - x, y]
    end
  end

  def fold_y(point, coord)
    x, y = point

    if y < coord
      point
    else
      [x, 2 * coord - y]
    end
  end
end
