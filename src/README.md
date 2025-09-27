# Divide and Conquer Algorithms Project




---

## Learning Goals
- Implement classic divide-and-conquer algorithms with safe recursion patterns.
- Analyse running-time recurrences using Master Theorem (3 cases) and Akra–Bazzi intuition; validate with measurements.
- Collect metrics (time, recursion depth, comparisons/allocations) and communicate results via a short report and clean Git history.

---

## Algorithms Implemented

### 1. MergeSort (D&C, Master Case 2)
- **Method:** Linear merge; small-n cut-off (insertion sort optional); reusable buffer.
- **Recurrence:** \(T(n) = 2T(n/2) + Θ(n)\), Master Case 2 ⇒ \(Θ(n \log n)\)
- **Notes:** Depth tracked via static counter; comparisons counted during merge operations.
- **Metrics:** Execution time grows approximately O(n log n); recursion depth grows logarithmically; number of comparisons matches theoretical expectations.

### 2. QuickSort (Robust)
- **Method:** Randomised pivot; recurse on smaller partition, iterate on larger (bounded stack ≈ O(log n) typical).
- **Recurrence:** \(T(n) = T(k) + T(n-k-1) + Θ(n)\) for partition size k; Master/Akra–Bazzi intuition ⇒ \(Θ(n \log n)\) average.
- **Notes:** Recursion depth bounded; comparisons counted during partitioning.
- **Metrics:** Faster than MergeSort on tested large n due to in-place operations and cache locality.

### 3. Deterministic Select (Median-of-Medians, O(n))
- **Method:** Groups of 5, median-of-medians as pivot, in-place partition; recurse only into necessary side.
- **Recurrence:** \(T(n) ≤ T(n/5) + T(7n/10) + Θ(n)\) ⇒ \(Θ(n)\)
- **Notes:** Guarantees linear time selection; metrics include execution time; recursion depth limited due to smaller-side recursion.
- **Metrics:** Slower than QuickSort/MergeSort for large n but guarantees exact k-th element.

### 4. Closest Pair of Points (2D, O(n log n))
- **Method:** Sort points by x; recursive split; strip check by y order (7-8 neighbor scan).
- **Recurrence:** \(T(n) = 2T(n/2) + Θ(n)\) ⇒ \(Θ(n \log n)\)
- **Notes:** Recursion depth and comparisons counted; validation against brute-force for small n.
- **Metrics:** Time grows faster than sorting algorithms due to 2D pair comparisons; depth grows logarithmically; comparisons increase with n.

---

## Metrics Collected

| Algorithm             | n    | Time (ns)  | Recursion Depth | Comparisons/Allocations |
|----------------------|------|------------|----------------|------------------------|
| MergeSort            | 100  | 128,500    | 8              | 537                    |
| MergeSort            | 1000 | 2,159,300  | 11             | 8,710                  |
| MergeSort            | 5000 | 2,652,200  | 14             | 55,202                 |
| QuickSort            | 100  | 214,700    | 5              | 752                    |
| QuickSort            | 1000 | 846,500    | 7              | 10,452                 |
| QuickSort            | 5000 | 1,825,300  | 9              | 70,868                 |
| DeterministicSelect  | 100  | 805,900    | N/A            | N/A                    |
| DeterministicSelect  | 1000 | 4,013,900  | N/A            | N/A                    |
| DeterministicSelect  | 5000 | 6,757,200  | N/A            | N/A                    |
| ClosestPair          | 100  | 972,900    | 6              | 134                    |
| ClosestPair          | 1000 | 6,626,100  | 9              | 1,747                  |
| ClosestPair          | 5000 | 14,522,300 | 12             | 6,734                  |

---

## Plots (time vs n, depth vs n)
*(Attach charts here if generated; describe trends briefly)*

- MergeSort & QuickSort: time roughly O(n log n); QuickSort faster on large n due to cache and in-place behavior.
- Deterministic Select: time linear but higher constant factor.
- ClosestPair: grows with n, depth logarithmic, comparisons increase significantly.

---

## Summary
- MergeSort and QuickSort follow expected theoretical behavior.
- QuickSort outperforms MergeSort in measured times due to memory locality.
- Deterministic Select guarantees linear selection but slower for large n.
- ClosestPair’s time grows faster due to 2D comparisons, but recursion depth remains moderate.
- Overall, measured metrics align well with theoretical analysis.

---

## GitHub Workflow

- **Branches:**
    - `main` – working releases (tags: v0.1, v1.0)
    - `feature/mergesort`, `feature/quicksort`, `feature/select`, `feature/closest`, `feature/metrics`

- **Commit storyline:**
    - `init` – Maven, JUnit5, README
    - `feat(metrics)` – counters, depth tracker
    - `feat(mergesort)` – baseline + buffer + cutoff + tests
    - `feat(quicksort)` – smaller-first recursion, randomized pivot + tests
    - `feat(select)` – deterministic select (MoM5) + tests
    - `feat(closest)` – divide-and-conquer + tests
    - `docs(report)` – metrics, analysis, plots
    - `release v1.0` – final submission

---

## Testing Notes
- Sorting algorithms validated on random and adversarial arrays; recursion depth bounded.
- Deterministic Select validated against `Arrays.sort(arr)[k]` over 100 trials.
- Closest Pair validated against brute-force for small n (≤2000); fast version for large n.  
