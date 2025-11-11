# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## [2025.11.11]

### Added
- Created SkillTreeSecondary abstract class implementing secondary operations (availableSkills and depth) using only kernel methods and standard OSU component behavior.
- Added removeSkill(String skill) to SkillTreeKernel to support full editability of the skill graph.
- Added skillExists(String) and allSkills() support usage in secondary logic.
- Finalized availableSkills() contract to use OSU-compliant Set semantics (aliases preserved where required).

### Updated
- Refined SkillTreeKernel and SkillTree method specifications.
- Clarified unlock semantics and prerequisites relationships to support recursive depth computation in SkillTreeSecondary.

## [2025.10.23]

### Added
- Created `SkillTreeKernel` and `SkillTree` interfaces following OSU component discipline.

### Updated
- Defined method contracts with clear parameter modes and @ensures clauses.
- Clarified hierarchy structure and documentation for client-facing API.

## [2025.10.17]

### Added

- Designed a PriorityQueue component
- Designed a SkillTree component
- Designed a PrefixTree component