<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="employeeName">
        <el-input
          v-model="queryParams.employeeName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门" prop="employeePosition">
        <el-input
          v-model="queryParams.employeePosition"
          placeholder="请输入部门"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="入职日期" prop="startDate">
        <el-date-picker clearable
                        v-model="queryParams.startDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择入职日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="离职日期" prop="endDate">
        <el-date-picker clearable
                        v-model="queryParams.endDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择离职日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['mine:employee_information:add']"
        >新增</el-button>
      </el-col>
      <!--      [导入]第一步:添加一个导入的按钮↓-->
      <el-col :span="1.5">
        <!--            v-hasPermi =权限字符 ,在数据库 sys_menu 内补足展示按钮以及权限字符:daoru 即可限制哪些角色有权限使用该按钮-->
        <!--        @click="dianji_daoru" 中的 dianji_daoru 为点击按钮产生的事件 可以自行修改事件名称-->
        <el-button
          v-hasPermi="['mine:employee_information:daoru']"
          icon="el-icon-upload2"
          size="mini"
          type="info"
          @click="dianji_daoru"
        >导入
        </el-button>
      </el-col>
      <!--      [导入]第一步:添加一个导入的按钮↑-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['mine:employee_information:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['mine:employee_information:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mine:employee_information:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employee_informationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="员工ID" align="center" prop="employeeId" />
      <el-table-column label="姓名" align="center" prop="employeeName" />
      <el-table-column label="部门" align="center" prop="employeePosition" />
      <el-table-column label="入职日期" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="离职日期" align="center" prop="endDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mine:employee_information:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mine:employee_information:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改员工信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="employeeName">
          <el-input v-model="form.employeeName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="部门" prop="employeePosition">
          <el-input v-model="form.employeePosition" placeholder="请输入部门" />
        </el-form-item>
        <el-form-item label="入职日期" prop="startDate">
          <el-date-picker clearable
                          v-model="form.startDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择入职日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="离职日期" prop="endDate">
          <el-date-picker clearable
                          v-model="form.endDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择离职日期">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- [导入]第二步]设计弹窗样式↓ -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" append-to-body width="400px">
      <el-upload
        ref="upload"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :auto-upload="false"
        :disabled="upload.isUploading"
        :headers="upload.headers"
        :limit="1"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        accept=".xlsx, .xls"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div slot="tip" class="el-upload__tip text-center">
          <!--          注释掉这段代码则是因为我在实用中没有设计为导入的表格覆盖原数据,意味着如果导入的表格中有部分关键项内的数据跟原数据有重复的话是会报错处理的,这部分根据各位的实际情况自行调整-->
          <!--          <div slot="tip" class="el-upload__tip">-->
          <!--            <el-checkbox v-model="upload.updateSupport"/>-->
          <!--            是否更新已经存在的用户数据-->
          <!--          </div>-->
          <!--          注释掉这段代码则是因为我在实用中没有设计为导入的表格覆盖原数据,意味着如果导入的表格中有部分关键项内的数据跟原数据有重复的话是会报错处理的,这部分根据各位的实际情况自行调整-->
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link :underline="false" style="font-size:12px;vertical-align: baseline;" type="primary"
                   @click="xiazaidaorumuban">下载模板
          </el-link>
          <!--          @click="xiazaidaorumuban"> 中的 xiazaidaorumuban 是调用函数 如果此处做了修改那么后续函数名也要对应修改-->
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="queren_daoru">确 定</el-button>
        <!--        @click="queren_daoru"> 中的 queren_daoru 也是事件名  如果此处做修改 后续也需要对应修改-->
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
    <!-- [导入]第二步]设计弹窗样式↑ -->
  </div>
</template>

<script>
import { listEmployee_information, getEmployee_information, delEmployee_information, addEmployee_information, updateEmployee_information } from "@/api/mine/employee_information";
// [导入]第三步: 添加import↓
import {getToken} from "@/utils/auth";
// [导入]第三步: 添加import↑
export default {
  name: "Employee_information",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 员工信息表格数据
      employee_informationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        employeeName: null,
        employeePosition: null,
        startDate: null,
        endDate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      // [导入]第四步:设置导入弹窗相关↓
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "导入表格",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: {Authorization: "Bearer " + getToken()},
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/mine/employee_information/importData"
      }
      // [导入]第四步:设置导入弹窗相关↑
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询员工信息列表 */
    getList() {
      this.loading = true;
      listEmployee_information(this.queryParams).then(response => {
        this.employee_informationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        employeeId: null,
        employeeName: null,
        employeePosition: null,
        startDate: null,
        endDate: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.employeeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加员工信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const employeeId = row.employeeId || this.ids
      getEmployee_information(employeeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改员工信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.employeeId != null) {
            updateEmployee_information(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEmployee_information(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const employeeIds = row.employeeId || this.ids;
      this.$modal.confirm('是否确认删除员工信息编号为"' + employeeIds + '"的数据项？').then(function() {
        return delEmployee_information(employeeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mine/employee_information/export', {
        ...this.queryParams
      }, `employee_information_${new Date().getTime()}.xlsx`)
    },
    // [导入]第五步:配置对应的事件函数↓
    /** 导入按钮操作 */
    dianji_daoru() {
      this.upload.title = "表格导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    xiazaidaorumuban() {
      this.download('mine/employee_information/importTemplate', {}, `表格_${new Date().getTime()}.xlsx`)  // 表格_${new Date().getTime()}.xlsx 代表下载的模板的表格名字可以自己修改
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", {dangerouslyUseHTMLString: true});
      this.getList();
    },
    // 提交上传文件
    queren_daoru() {
      this.$refs.upload.submit();
    }
    // [导入]第五步:配置对应的事件函数↑
  }
};
</script>
