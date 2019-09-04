Master is golden; only the merge master touches it.

There is a dev branch, taken initially from master, that all devs work off. Instead of having a branch per developer, we make feature, or ticket, branches from dev.

For every discreet feature (bug, enhancement, etc.), a new local branch is made from dev. Developers don't have to work on the same branch, since each feature branch is scoped to only what that single developer is working on. This is where git's cheap branching comes in handy.

Once the feature is ready, it's merged locally back into dev and pushed up to the cloud. Everyone keeps in sync by pulling on dev often.
