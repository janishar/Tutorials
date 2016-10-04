package ali.jpostnetworking.test.jpostnetworking;

import java.util.List;

/**
 * Created by janisharali on 03/10/16.
 */

public class GitRepoMsg {

    private List<GitRepo> gitRepoList;

    public GitRepoMsg(List<GitRepo> gitRepoList) {
        this.gitRepoList = gitRepoList;
    }

    public List<GitRepo> getGitRepoList() {
        return gitRepoList;
    }

    public void setGitRepoList(List<GitRepo> gitRepoList) {
        this.gitRepoList = gitRepoList;
    }
}
