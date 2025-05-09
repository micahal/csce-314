{-
deepseq ex
-}
import Control.DeepSeq(deepseq)

--a function that simulates expensive computation
expFun :: Int -> Int
expFun x = x * 2

--lazy evalutation
lazyExample :: [Int]
lazyExample = map expFun [1..5]

-- using seq for WHNF
seqExample :: Int
seqExample = 
    let nums = map expFun [1..5]
    in nums `seq` length nums

deepseqExample :: Int
deepseqExample = 
    let nums = map expFun [1..5]
    in nums `deepseq` length nums -- fully evaluates all elements

main :: IO()
main = do
    putStrLn "Lazy evalutation (thunks remain until needed)"
    print lazyExample --only computated when printed
    putStrLn "\nUsing seq (forcing list structure but not elements)"
    print seqExample -- shows list is created
    putStrLn "\nUsing deepseq"
    print deepseqExample --fully evals list before printing