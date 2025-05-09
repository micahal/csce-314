{-Micah Alummoottil
  2/17/25
  by entering your name and date you certify this is your
  own work and not that of any other person or service.
  Comment your functions for clarity.-}

--ToDo  Define an ADT (Abstract Data Type) for Shape.
--Implement polymorphism using Haskell's type classes.
--Implement methods for calculating the area and perimeter of various shapes.
--Use recursion and higher-order functions to manage a collection of shapes.
--add functionality to calculate volume

--defining our data type (Polymorphism)
data Shape = Circle Double -- passing in radius
    | Rectangle Double Double Double -- passing in length, height, width
    | Triangle Double Double Double Double Double Double --passing in base, height, sidea, sideb, sidec, height2(height of the prism)
    | Pentagon Double Double Double --passing in length of side, base area, height
    | Hexagon Double Double Double --passing in length of side, base area, height
    deriving (Show, Read)
--area for each shape
area :: Shape -> Double
area (Circle r) = pi * r * r
area (Rectangle l w _) = w * l
area (Triangle b h _ _ _ _) = b * h
area (Pentagon s _ _) = 0.25 * sqrt (5 * (5 + 2 * sqrt 5)) * s * s
area (Hexagon s _ _) = (3 * sqrt 3 / 2) * s * s

--perimeter for each shape
perimeter :: Shape -> Double
perimeter (Circle r) = (2 * pi * r) 
perimeter (Rectangle l w _) = (2 * l + 2 * w)
perimeter (Triangle _ _ a b2 c _) = (a + b2 + c)
perimeter (Pentagon s _ _) = (s * 5)
perimeter (Hexagon s _ _) = (s * 6)
--functionality to calculate volume
volume :: Shape -> Double
volume (Circle r) = ((4 / 3) * pi * r**3) --sphere
volume (Rectangle l w h) = (h * w * l) --rectangular prism
volume (Triangle b h _ _ _ h2) = (b * h * h2 * 0.5) -- triangular prism
volume (Pentagon _ a h) = (a * h) --pentagonal prism
volume (Hexagon _ a h) =  (a * h) --hexagonal prism

--using recursion/map for total area, total perm, total volume
totalArea :: [Shape] -> Double
totalArea [] = 0
totalArea (n:nn) = area n + totalArea nn

totalPerimeter :: [Shape] -> Double
totalPerimeter [] = 0
totalPerimeter (n:nn) = perimeter n + totalPerimeter nn

totalVolume :: [Shape] -> Double
totalVolume [] = 0
totalVolume (n:nn) = volume n + totalVolume nn

--to make our output look nice
names :: [String]
names = ["circle", "rectangle", "triangle", "pentagon", "hexagon"]

-- Create a simple main program
main :: IO ()
main = do
    -- Hardcoded list of shapes
    let shapes = [Circle 10, Rectangle 5 8 7, Triangle 3 4 5 6 7 8, Pentagon 5 8 9, Hexagon 2 4 3]
    
    -- Print out the area and perimeter of each shape
    putStrLn "Shape Areas:"
    mapM_ (\(s, name) -> putStrLn $ show (area s) ++ " " ++ name) (zip shapes names)
   -- go through your list and print all of them with one line of code.


    putStrLn "\nShape Perimeters:"
    mapM_ (\(s, name) -> putStrLn $ show (perimeter s) ++ " " ++ name) (zip shapes names)
   -- go through your list and print all of them with one line of code.

    putStrLn "\nShape Volumes:"
    mapM_ (\(s, name) -> putStrLn $ show (volume s) ++ " " ++ name) (zip shapes names)
   -- go through your list and print all of them with one line of code.
    
    -- Print out the total area and perimeter of all shapes
    putStrLn "\nTotal Area of All Shapes:"
    print (totalArea shapes)
    
    putStrLn "Total Perimeter of All Shapes:"
    print (totalPerimeter shapes)

    putStrLn "Total volume of All Shapes:"
    print (totalVolume shapes)